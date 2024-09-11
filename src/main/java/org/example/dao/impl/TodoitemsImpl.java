package org.example.dao.impl;

import com.sun.tools.javac.comp.Todo;
import org.example.dao.Todoitems;
import org.example.db.MySQLConnection;
import org.example.model.Person;
import org.example.model.Todo_item;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoitemsImpl implements Todoitems {
    Connection connection = MySQLConnection.getConnection();

    @Override
    public Todo_item create(Todo_item todoItem) {
        String CREATE_TODO_ITEM = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TODO_ITEM, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getAssignee_id());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                todoItem.setTodo_id(resultSet.getInt(1));
            }

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Todo create(Todo todoItem) {
        return null;
    }

    @Override
    public List<Todo_item> findAll() {
        List<Todo_item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item");

            while (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setAssignee_id(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Todo_item findById(int todoItemId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE todo_item_id = ?");
            preparedStatement.setInt(1, todoItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setTodo_id(resultSet.getInt("todo_item_id"));
                return todoItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Todo_item> findByDoneStatus(boolean done) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE done = ?");
            preparedStatement.setBoolean(1, done);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Todo_item> result = new ArrayList<>();
            while (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setTodo_id(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Todo_item> findByAssignee(int assigneeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id = ?");
            preparedStatement.setInt(1, assigneeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Todo_item> result = new ArrayList<>();
            while (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setTodo_id(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Todo_item> findByAssignee(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id = ?");
            preparedStatement.setInt(1, person.getPerson_id());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Todo_item> result = new ArrayList<>();
            while (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setTodo_id(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Todo_item> findByUnassignedTodoItems() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id IS NULL");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Todo_item> result = new ArrayList<>();
            while (resultSet.next()) {
                Todo_item todoItem = new Todo_item(
                        resultSet.getInt("todo_item_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItem.setTodo_id(resultSet.getInt("todo_item_id"));
                result.add(todoItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }

    @Override
    public Todo_item update(Todo_item todoItem) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_item_id = ?");
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getAssignee_id());
            preparedStatement.setInt(6, todoItem.getTodo_id());

            preparedStatement.executeUpdate();

            return todoItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean deleteById(int todoId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM todo_item WHERE todo_item_id = ?");
            preparedStatement.setInt(1, todoId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
