package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public void insertPerson(String name, String lastName, String email, String phoneNum) {
        String query = "INSERT INTO Person (Name, LastName, Email, PhoneNum) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNum);

            stmt.executeUpdate();
            System.out.println("Person inserted successfully.");

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Unique constraint violation
                System.out.println("Error: A person with this email (" + email + ") already exists.");
            } else {
                System.out.println("An unexpected error occurred while inserting the person: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Person> getAllPersons() {
        String query = "SELECT * FROM Person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("PersonID"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNum")
                ));
            }
            System.out.println("Persons retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving persons: " + e.getMessage());
        }

        return persons;
    }
}


