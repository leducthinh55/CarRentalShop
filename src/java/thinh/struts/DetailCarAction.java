/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.util.List;
import thinh.car.CarDAO;
import thinh.car.CarDTO;
import thinh.feedbacks.FeedbackDAO;
import thinh.feedbacks.FeedbackDTO;

/**
 *
 * @author thinh
 */
public class DetailCarAction {
    private final String SUCCESS = "success";
    private int carIdDetail;
    private List<FeedbackDTO> listFeedbacks;
    private CarDTO carDTO;
    public DetailCarAction() {
    }
    
    public String execute() throws Exception {
        CarDAO carDAO = new CarDAO();
        carDTO = carDAO.getCarById(carIdDetail);
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        listFeedbacks = feedbackDAO.getListFeedbacksByCarId(carIdDetail);
        return SUCCESS;
    }

    public int getCarIdDetail() {
        return carIdDetail;
    }

    public void setCarIdDetail(int carIdDetail) {
        this.carIdDetail = carIdDetail;
    }

    public List<FeedbackDTO> getListFeedbacks() {
        return listFeedbacks;
    }

    public void setListFeedbacks(List<FeedbackDTO> listFeedbacks) {
        this.listFeedbacks = listFeedbacks;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }
    
}
