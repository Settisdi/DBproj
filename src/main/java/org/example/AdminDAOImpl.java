package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public void insertAdmin(Admin admin) {
        String query = "INSERT INTO Admin (AdminID, UserName, PassWord) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, admin.getAdminID());
            stmt.setString(2, admin.getUserName());
            stmt.setString(3, admin.getPassword());

            stmt.executeUpdate();
            System.out.println("Admin inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting the admin: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        String query = "SELECT * FROM Admin";
        List<Admin> admins = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("UserName"),
                        rs.getString("PassWord")
                ));
            }
            System.out.println("Admins retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving admins: " + e.getMessage());
        }

        return admins;
    }

    @Override
    public void deleteAdmin(int adminID) {
        String query = "DELETE FROM Admin WHERE AdminID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, adminID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No admin found with ID: " + adminID);
            } else {
                System.out.println("Admin deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete admin with ID (" + adminID + ") because it is referenced by other records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the admin: " + e.getMessage());
            }
        }
    }
}

