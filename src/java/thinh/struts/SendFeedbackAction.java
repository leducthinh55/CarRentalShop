/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import thinh.feedbacks.FeedbackDAO;

/**
 *
 * @author thinh
 */
public class SendFeedbackAction {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int rating;
    private int carIdDetail;
    private String description;
    private int carIdFeedback;
    private String userFeedback;

    public SendFeedbackAction() {
    }

    public String execute() throws Exception {
        if (!userFeedback.equals("")) {
            Timestamp curTime = Timestamp.valueOf(LocalDateTime.now());
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            boolean check = feedbackDAO.addFeedbackHaveRating(userFeedback, carIdFeedback, rating, description, curTime);
            if (check) {
                carIdDetail = carIdFeedback;
                return SUCCESS;
            }
        }
        return FAIL;
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

    public int getCarIdFeedback() {
        return carIdFeedback;
    }

    public void setCarIdFeedback(int carIdFeedback) {
        this.carIdFeedback = carIdFeedback;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public int getCarIdDetail() {
        return carIdDetail;
    }

    public void setCarIdDetail(int carIdDetail) {
        this.carIdDetail = carIdDetail;
    }
    
}
