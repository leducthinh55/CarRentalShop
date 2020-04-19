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
public class AdminPageAction {
    private final String SUCCESS = "success";
    private List<CarDTO> listCarAdmin;
    public AdminPageAction() {
        
    }
    
    public String execute() throws Exception {
        Map servletContext = ActionContext.getContext().getApplication();
        CarDAO carDAO = new CarDAO();
        listCarAdmin = carDAO.searchAll();
        servletContext.put("LIST_CAR_ADMIN", listCarAdmin);
        return SUCCESS;
    }

    public List<CarDTO> getListCarAdmin() {
        return listCarAdmin;
    }

    public void setListCarAdmin(List<CarDTO> listCarAdmin) {
        this.listCarAdmin = listCarAdmin;
    }
    
}
