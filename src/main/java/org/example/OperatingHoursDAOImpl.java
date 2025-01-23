package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperatingHoursDAOImpl implements OperatingHoursDAO {

    @Override
    public void insertOperatingHours(OperatingHours hours) {
        String query = "INSERT INTO OperatingHours (RestaurantID, DayOfWeek, OpenTime, CloseTime) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hours.getRestaurantID());
            stmt.setString(2, hours.getDayOfWeek());
            stmt.setString(3, hours.getOpenTime());
            stmt.setString(4, hours.getCloseTime());

            stmt.executeUpdate();
            System.out.println("Operating hours inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting operating hours: " + e.getMessage());
        }
    }

    @Override
    public List<OperatingHours> getOperatingHoursByRestaurant(int restaurantID) {
        String query = "SELECT * FROM OperatingHours WHERE RestaurantID = ?";
        List<OperatingHours> hoursList = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, restaurantID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hoursList.add(new OperatingHours(
                            rs.getInt("OperatingHoursID"),
                            rs.getInt("RestaurantID"),
                            rs.getString("DayOfWeek"),
                            rs.getString("OpenTime"),
                            rs.getString("CloseTime")
                    ));
                }
            }
            System.out.println("Operating hours retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving operating hours: " + e.getMessage());
        }

        return hoursList;
    }

    @Override
    public void updateOperatingHours(int id, String dayOfWeek, String openTime, String closeTime) {
        String query = "UPDATE OperatingHours SET DayOfWeek = ?, OpenTime = ?, CloseTime = ? WHERE OperatingHoursID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dayOfWeek);
            stmt.setString(2, openTime);
            stmt.setString(3, closeTime);
            stmt.setInt(4, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No operating hours found with ID: " + id);
            } else {
                System.out.println("Operating hours updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while updating operating hours: " + e.getMessage());
        }
    }

    @Override
    public void deleteOperatingHours(int id) {
        String query = "DELETE FROM OperatingHours WHERE OperatingHoursID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No operating hours found with ID: " + id);
            } else {
                System.out.println("Operating hours deleted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while deleting operating hours: " + e.getMessage());
        }
    }
}

