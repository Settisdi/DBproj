package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {

    @Override
    public void insertDiscount(Discount discount) {
        String query = "INSERT INTO Discount (DiscountPrice) VALUES (?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, discount.getDiscountPrice());

            stmt.executeUpdate();
            System.out.println("Discount inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting the discount: " + e.getMessage());
        }
    }

    @Override
    public void updateDiscount(int discountID, int discountPrice) {
        String query = "UPDATE Discount SET DiscountPrice = ? WHERE DiscountID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, discountPrice);
            stmt.setInt(2, discountID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No discount found with ID: " + discountID);
            } else {
                System.out.println("Discount updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while updating the discount: " + e.getMessage());
        }
    }

    @Override
    public void deleteDiscount(int discountID) {
        String query = "DELETE FROM Discount WHERE DiscountID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, discountID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No discount found with ID: " + discountID);
            } else {
                System.out.println("Discount deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete discount with ID (" + discountID + ") because it is referenced by other records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the discount: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Discount> getAllDiscounts() {
        String query = "SELECT * FROM Discount";
        List<Discount> discounts = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                discounts.add(new Discount(
                        rs.getInt("DiscountID"),
                        rs.getInt("DiscountPrice")
                ));
            }
            System.out.println("Discounts retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving discounts: " + e.getMessage());
        }

        return discounts;
    }
}

