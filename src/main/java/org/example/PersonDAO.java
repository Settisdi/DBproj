package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public interface PersonDAO {
    void insertPerson(String name, String lastName, String email, String phoneNum);
    List<Person> getAllPersons();
}

