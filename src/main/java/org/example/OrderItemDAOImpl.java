package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public void insertOrderItem(OrderItem item) {
        String query = "INSERT INTO OrderItem (CartID, FeedBackID, ItemCategoryID) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, item.getCartID());
            stmt.setInt(2, item.getFeedbackID());
            stmt.setInt(3, item.getItemCategoryID());

            stmt.executeUpdate();
            System.out.println("Order item inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting the order item: " + e.getMessage());
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByCartID(int cartID) {
        String query = "SELECT * FROM OrderItem WHERE CartID = ?";
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderItems.add(new OrderItem(
                            rs.getInt("OrderItemID"),
                            rs.getInt("CartID"),
                            rs.getInt("FeedBackID"),
                            rs.getInt("ItemCategoryID")
                    ));
                }
            }
            System.out.println("Order items retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving order items: " + e.getMessage());
        }

        return orderItems;
    }

    @Override
    public void deleteOrderItem(int orderItemID) {
        String query = "DELETE FROM OrderItem WHERE OrderItemID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItemID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No order item found with ID: " + orderItemID);
            } else {
                System.out.println("Order item deleted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while deleting the order item: " + e.getMessage());
        }
    }
}

