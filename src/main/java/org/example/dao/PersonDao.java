package org.example.dao;

import org.example.entity.Person;

import java.util.List;

public interface PersonDao {
    List<Person> findAll();
    void addPerson(String fname, String lname);
    void deleteById(int id);
    Person findById(int id);
    List<Person> findByFnameContaining(String n);
    List<Person> findByName(String fname, String lname);
    List<Person> findSortedByFname();
}


