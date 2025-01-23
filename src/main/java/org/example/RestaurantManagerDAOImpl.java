package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManagerDAOImpl implements RestaurantManagerDAO {

    @Override
    public void insertRestaurantManager(RestaurantManager manager) {
        String query = "INSERT INTO RestaurantManager (RestaurantManagerID, RestaurantID) VALUES (?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, manager.getManagerID());
            stmt.setInt(2, manager.getRestaurantID());

            stmt.executeUpdate();
            System.out.println("Restaurant Manager inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting the restaurant manager: " + e.getMessage());
        }
    }

    @Override
    public List<RestaurantManager> getAllRestaurantManagers() {
        String query = "SELECT * FROM RestaurantManager";
        List<RestaurantManager> managers = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                managers.add(new RestaurantManager(
                        rs.getInt("RestaurantManagerID"),
                        rs.getInt("RestaurantID")
                ));
            }
            System.out.println("Restaurant Managers retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving restaurant managers: " + e.getMessage());
        }

        return managers;
    }

    @Override
    public void deleteRestaurantManager(int managerID) {
        String query = "DELETE FROM RestaurantManager WHERE RestaurantManagerID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, managerID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No restaurant manager found with ID: " + managerID);
            } else {
                System.out.println("Restaurant Manager deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete restaurant manager with ID (" + managerID + ") because it is referenced by other records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the restaurant manager: " + e.getMessage());
            }
        }
    }
}

