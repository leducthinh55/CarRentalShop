/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.feedbacks;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author thinh
 */
public class FeedbackDTO implements Serializable{
    private int feedbackId;
    private String email;
    private int carId;
    private int rating;
    private String description;
    private Timestamp timeFeedback;

    public FeedbackDTO(int feedbackId, String email, int carId, int rating, String description, Timestamp timeFeedback) {
        this.feedbackId = feedbackId;
        this.email = email;
        this.carId = carId;
        this.rating = rating;
        this.description = description;
        this.timeFeedback = timeFeedback;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimeFeedback() {
        return timeFeedback;
    }

    public void setTimeFeedback(Timestamp timeFeedback) {
        this.timeFeedback = timeFeedback;
    }
    
}
