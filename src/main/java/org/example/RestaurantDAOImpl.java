package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {
    @Override
    public void insertRestaurant(String name, double minPurchase, double deliveryFee, String city, String address) {
        String query = "CALL sp_insert_restaurant(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, minPurchase);
            stmt.setDouble(3, deliveryFee);
            stmt.setString(4, city);
            stmt.setString(5, address);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getRestaurantsPaged(int offset, int limit) {
        String query = "CALL sp_get_restaurants_paged(?, ?)";
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Restaurant restaurant = new Restaurant(
                            rs.getInt("RestaurantID"),
                            rs.getString("Name"),
                            rs.getDouble("MinPurchase"),
                            rs.getDouble("DeliveryFee"),
                            rs.getString("City"),
                            rs.getString("Address")
                    );
                    restaurants.add(restaurant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public void updateRestaurant(int id, String name, double minPurchase, double deliveryFee, String city, String address) {
        String query = "CALL sp_update_restaurant(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setDouble(3, minPurchase);
            stmt.setDouble(4, deliveryFee);
            stmt.setString(5, city);
            stmt.setString(6, address);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No restaurant found with ID: " + id);
            } else {
                System.out.println("Restaurant updated successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) { // Custom error raised by the stored procedure
                System.out.println("Error: No restaurant found with the given ID (" + id + ").");
            } else {
                System.out.println("An unexpected error occurred while updating the restaurant: " + e.getMessage());
            }
        }
    }



    @Override
    public void deleteRestaurant(int id) {
        String query = "CALL sp_delete_restaurant(?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No restaurant found with ID: " + id);
            } else {
                System.out.println("Restaurant deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) { // Custom error from the stored procedure
                System.out.println("Error: No restaurant found with the given ID (" + id + ").");
            } else if (e.getSQLState().equals("23000")) { // Foreign key violation
                System.out.println("Error: Cannot delete restaurant with ID (" + id + ") because it has dependent records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the restaurant: " + e.getMessage());
            }
        }
    }


}

