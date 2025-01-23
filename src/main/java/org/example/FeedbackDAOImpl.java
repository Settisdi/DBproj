package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOImpl implements FeedbackDAO {

    @Override
    public void insertFeedback(Feedback feedback) {
        String query = "INSERT INTO Feedback (PersonID, Rating, Comments) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, feedback.getPersonID());
            stmt.setInt(2, feedback.getRating());
            stmt.setString(3, feedback.getComments());

            stmt.executeUpdate();
            System.out.println("Feedback inserted successfully.");

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key or unique constraint violation
                System.out.println("Error: Feedback for this person already exists or PersonID is invalid.");
            } else {
                System.out.println("An unexpected error occurred while inserting feedback: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Feedback> getFeedbackByPersonID(int personID) {
        String query = "SELECT * FROM Feedback WHERE PersonID = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, personID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    feedbackList.add(new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("PersonID"),
                            rs.getInt("Rating"),
                            rs.getString("Comments"),
                            rs.getTimestamp("CreatedAt").toLocalDateTime()
                    ));
                }
            }
            System.out.println("Feedback retrieved successfully for PersonID: " + personID);

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving feedback: " + e.getMessage());
        }

        return feedbackList;
    }

    @Override
    public void updateFeedback(int feedbackID, int rating, String comments) {
        String query = "UPDATE Feedback SET Rating = ?, Comments = ? WHERE FeedbackID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, rating);
            stmt.setString(2, comments);
            stmt.setInt(3, feedbackID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No feedback found with ID: " + feedbackID);
            } else {
                System.out.println("Feedback updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("An unexpected error occurred while updating feedback: " + e.getMessage());
        }
    }

    @Override
    public void deleteFeedback(int feedbackID) {
        String query = "DELETE FROM Feedback WHERE FeedbackID = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, feedbackID);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No feedback found with ID: " + feedbackID);
            } else {
                System.out.println("Feedback deleted successfully.");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Foreign key constraint violation
                System.out.println("Error: Cannot delete feedback with ID (" + feedbackID + ") because it is referenced by other records.");
            } else {
                System.out.println("An unexpected error occurred while deleting feedback: " + e.getMessage());
            }
        }
    }
}

