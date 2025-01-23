package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressesDAOImpl implements AddressesDAO {

    @Override
    public void insertAddress(Address address) {
        String query = "INSERT INTO Addresses (PersonID, Street, City, ExactAddress, PostalCode, IsPrimary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, address.getPersonID());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getExactAddress());
            stmt.setString(5, address.getPostalCode());
            stmt.setBoolean(6, address.isPrimary());

            stmt.executeUpdate();
            System.out.println("Address inserted successfully.");

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key or unique constraint violation
                System.out.println("Error: Could not insert address. Ensure PersonID is valid.");
            } else {
                System.out.println("An unexpected error occurred while inserting the address: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Address> getAddressesByPersonID(int personID) {
        String query = "SELECT * FROM Addresses WHERE PersonID = ?";
        List<Address> addresses = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, personID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    addresses.add(new Address(
                            rs.getInt("AddressID"),
                            rs.getInt("PersonID"),
                            rs.getString("Street"),
                            rs.getString("City"),
                            rs.getString("ExactAddress"),
                            rs.getString("PostalCode"),
                            rs.getBoolean("IsPrimary")
                    ));
                }
            }
            System.out.println("Addresses retrieved successfully for PersonID: " + personID);

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving addresses: " + e.getMessage());
        }

        return addresses;
    }

    @Override
    public void updateAddress(int addressID, String street, String city, String exactAddress, String postalCode, boolean isPrimary) {
        String query = "UPDATE Addresses SET Street = ?, City = ?, ExactAddress = ?, PostalCode = ?, IsPrimary = ? WHERE AddressID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, street);
            stmt.setString(2, city);
            stmt.setString(3, exactAddress);
            stmt.setString(4, postalCode);
            stmt.setBoolean(5, isPrimary);
            stmt.setInt(6, addressID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No address found with ID: " + addressID);
            } else {
                System.out.println("Address updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while updating the address: " + e.getMessage());
        }
    }

    @Override
    public void deleteAddress(int addressID) {
        String query = "DELETE FROM Addresses WHERE AddressID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, addressID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No address found with ID: " + addressID);
            } else {
                System.out.println("Address deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete address with ID (" + addressID + ") because it is referenced by other records.");
            } else {
                System.out.println("An unexpected error occurred while deleting the address: " + e.getMessage());
            }
        }
    }
}

