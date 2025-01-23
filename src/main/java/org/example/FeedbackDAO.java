package org.example;
import java.util.List;

public interface FeedbackDAO {
    void insertFeedback(Feedback feedback);
    List<Feedback> getFeedbackByPersonID(int personID);
    void updateFeedback(int feedbackID, int rating, String comments);
    void deleteFeedback(int feedbackID);
}

