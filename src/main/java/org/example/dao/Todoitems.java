package org.example.dao;

import com.sun.tools.javac.comp.Todo;
import org.example.model.Person;
import org.example.model.Todo_item;

import java.util.Collection;
import java.util.List;

public interface Todoitems {


    Todo_item create(Todo_item todoItem);

    Todo create(Todo todoItem);

    List<Todo_item> findAll();
    Todo_item findById(int todo_id);
    List<Todo_item> findByDoneStatus(boolean done);

    Collection<Todo_item> findByAssignee(int personId);

    Collection<Todo_item> findByAssignee(Person person);

    List<Todo_item> findByUnassignedTodoItems();
    Todo update(Todo todo);

    Todo_item update(Todo_item todoItem);

    boolean deleteById(int todo_id);

}
