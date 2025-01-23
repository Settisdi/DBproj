package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOImpl implements CategoriesDAO {

    @Override
    public void insertCategory(Categories category) {
        String query = "INSERT INTO Categories (RestaurantID, Name, Description) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, category.getRestaurantID());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());

            stmt.executeUpdate();
            System.out.println("Category inserted successfully.");

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Unique or foreign key constraint violation
                System.out.println("Error: Could not insert category. Ensure the restaurant ID is valid.");
            } else {
                System.out.println("An unexpected error occurred while inserting the category: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Categories> getCategoriesByRestaurant(int restaurantID) {
        String query = "SELECT * FROM Categories WHERE RestaurantID = ?";
        List<Categories> categories = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, restaurantID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Categories(
                            rs.getInt("CategoryID"),
                            rs.getInt("RestaurantID"),
                            rs.getString("Name"),
                            rs.getString("Description")
                    ));
                }
            }
            System.out.println("Categories retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving categories: " + e.getMessage());
        }

        return categories;
    }

    @Override
    public void updateCategory(int categoryID, String name, String description) {
        String query = "UPDATE Categories SET Name = ?, Description = ? WHERE CategoryID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, categoryID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No category found with ID: " + categoryID);
            } else {
                System.out.println("Category updated successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) { // Custom SQL error (if implemented)
                System.out.println("Error: No category found with the given ID (" + categoryID + ").");
            } else {
                System.out.println("An unexpected error occurred while updating the category: " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteCategory(int categoryID) {
        String query = "DELETE FROM Categories WHERE CategoryID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, categoryID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No category found with ID: " + categoryID);
            } else {
                System.out.println("Category deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete category with ID (" + categoryID + ") because it has dependent records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the category: " + e.getMessage());
            }
        }
    }
}

