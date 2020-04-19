/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import thinh.car.CarDAO;
import thinh.car.CarDTO;

/**
 *
 * @author thinh
 */
public class DeleteCarAction {
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private int carId;
    public DeleteCarAction() {
    }
    
    public String execute() throws Exception {
        CarDAO carDAO = new CarDAO();
        boolean check = carDAO.deleteCar(carId);
        if (check) {
            Map servletContext = ActionContext.getContext().getApplication();
            List<CarDTO> listCarAdmin = carDAO.searchAll();
            servletContext.put("LIST_CAR_ADMIN", listCarAdmin);
            List<CarDTO> listCar = carDAO.searchBaseOnNameAndStatus("", "active");
            servletContext.put("LIST_CAR", listCar);
            return SUCCESS;
        }
        return FAIL;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    
}
