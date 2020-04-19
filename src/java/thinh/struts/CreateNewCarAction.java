/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import thinh.car.CarDAO;
import thinh.car.CarDTO;
import thinh.category.CategoryDAO;

/**
 *
 * @author thinh
 */
public class CreateNewCarAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String INPUT = "input";
    private String carName;
    private String image;
    private String category;
    private String color;
    private double price;
    private int quantity;
    private String error;

    public CreateNewCarAction() {
    }

    public String execute() throws Exception {
        try {
            image = "images/" + image;
            CategoryDAO categoryDAO = new CategoryDAO();
            int categoryId = categoryDAO.getCategoryID(category);
            CarDAO carDAO = new CarDAO();
            String year = LocalDate.now().toString();
            boolean check = carDAO.addCar(carName, color, year, categoryId, image, price, quantity, "active");
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
                error = "Car Name is exist";
                return INPUT;
            }
        }
        return FAIL;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
