/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.util.List;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import thinh.car.CarDAO;
import thinh.car.CarDTO;
import thinh.category.CategoryDAO;

/**
 *
 * @author thinh
 */
public class HomePageAction {
    private List<CarDTO> listCar;
    private final String SUCCESS = "success";
    public HomePageAction() { 
    }
    
    public String execute() throws Exception {
        CarDAO carDAO = new CarDAO();
        listCar = carDAO.searchBaseOnNameAndStatus("", "active");
        ServletContext servletContext = ServletActionContext.getServletContext();
        servletContext.setAttribute("LIST_CAR", listCar);
        CategoryDAO categoryDAO = new CategoryDAO();
        List<String> listCategory = categoryDAO.getListCategory();
        servletContext.setAttribute("LIST_CATEGORY",listCategory);
        return SUCCESS;
    }

    public List<CarDTO> getListCar() {
        return listCar;
    }

    public void setListCar(List<CarDTO> listCar) {
        this.listCar = listCar;
    }
    
    
}
