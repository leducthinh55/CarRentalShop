/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import thinh.car.CarDAO;
import thinh.car.CarDTO;
import thinh.category.CategoryDAO;

/**
 *
 * @author thinh
 */
public class UpdateCarAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String INPUT = "input";
    private int carId;
    private String carName;
    private String color;
    private String category;
    private String image;
    private String imageHidden;
    private double price;
    private int quantity;
    private String status;
    private String error;

    public UpdateCarAction() {
    }

    public String execute() throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        int categoryId = categoryDAO.getCategoryID(category);
        if (image.equals("")) {
            image = imageHidden;
        } else {
            image= "images/"+ image;
        }
        try {
            CarDAO carDAO = new CarDAO();
            boolean check = carDAO.updateCar(carId, carName, color, categoryId, image, price, quantity, status);
            if (check) {
                Map servletContext = ActionContext.getContext().getApplication();
                List<CarDTO> listCarAdmin = carDAO.searchAll();
                servletContext.put("LIST_CAR_ADMIN", listCarAdmin);
                List<CarDTO> listCar = carDAO.searchBaseOnNameAndStatus("", "active");
                servletContext.put("LIST_CAR", listCar);
                return SUCCESS;
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate")) {
                error = "Car Name is existed";
                return INPUT;
            }
        }
        error = "Can not update!!";
        return FAIL;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageHidden() {
        return imageHidden;
    }

    public void setImageHidden(String imageHidden) {
        this.imageHidden = imageHidden;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
