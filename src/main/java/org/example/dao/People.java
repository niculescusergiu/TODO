package org.example.dao;

import org.example.model.Person;

import java.util.Collection;

public interface People {
   Person create (Person person);
   Collection<Person> findAll();
   Person findById(int person_id);
   Collection<Person> findByName(String first_name);
   Person update (Person person);
   boolean deleteById(int person_id);
}
