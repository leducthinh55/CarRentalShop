/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.util.List;
import thinh.car.CarDAO;
import thinh.car.CarDTO;

/**
 *
 * @author thinh
 */
public class SearchAdminAction {
    private String carName;
    private String category;
    private String status;
    private List<CarDTO> listSearchAdmin;
    private final String SUCCESS = "success";
    public SearchAdminAction() {
    }
    
    public String execute() throws Exception {
        CarDAO carDAO = new CarDAO();
        if(category.equals("all")) {
            if (status.equals("all")) {
                listSearchAdmin = carDAO.searchBaseOnName(carName);
            } else {
                listSearchAdmin = carDAO.searchBaseOnNameAndStatus(category, status);
            }
        } else {
            if (status.equals("all")) {
                listSearchAdmin = carDAO.searchBaseOnNameAndCategory(carName, category);
            } else {
                listSearchAdmin = carDAO.searchBaseOnNameAndCategoryAndStatus(carName, category, status);
            }
        }
        return SUCCESS;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CarDTO> getListSearchAdmin() {
        return listSearchAdmin;
    }

    public void setListSearchAdmin(List<CarDTO> listSearchAdmin) {
        this.listSearchAdmin = listSearchAdmin;
    }
    
    
}
