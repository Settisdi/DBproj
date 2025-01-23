package org.example;
import java.time.LocalDateTime;

public class Feedback {
    private int feedbackID;
    private int personID;
    private int rating;
    private String comments;
    private LocalDateTime createdAt;

    public Feedback(int feedbackID, int personID, int rating, String comments, LocalDateTime createdAt) {
        this.feedbackID = feedbackID;
        this.personID = personID;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

