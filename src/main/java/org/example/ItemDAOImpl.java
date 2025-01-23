package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public void insertItem(Item item) {
        String query = "CALL sp_insert_item(?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, item.getPrice());
            stmt.setString(2, item.getIngredient());
            stmt.setString(3, item.getName());
            stmt.setBytes(4, item.getPhoto());

            stmt.executeUpdate();
            System.out.println("Item inserted successfully.");

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while inserting the item: " + e.getMessage());
        }
    }

    @Override
    public List<Item> getAllItems() {
        String query = "SELECT * FROM Item";
        List<Item> items = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("ItemID"),
                        rs.getDouble("Price"),
                        rs.getString("Ingredient"),
                        rs.getString("Name"),
                        rs.getBytes("Photo")
                ));
            }
            System.out.println("Items retrieved successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving items: " + e.getMessage());
        }

        return items;
    }

    @Override
    public void updateItem(int itemID, String name, double price, String ingredient, byte[] photo) {
        String query = "UPDATE Item SET Name = ?, Price = ?, Ingredient = ?, Photo = ? WHERE ItemID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, ingredient);
            stmt.setBytes(4, photo);
            stmt.setInt(5, itemID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No item found with ID: " + itemID);
            } else {
                System.out.println("Item updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while updating the item: " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(int itemID) {
        String query = "DELETE FROM Item WHERE ItemID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No item found with ID: " + itemID);
            } else {
                System.out.println("Item deleted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while deleting the item: " + e.getMessage());
        }
    }
}

