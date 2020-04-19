/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import thinh.accounts.AccountDTO;
import thinh.renting.RentDTO;
import thinh.renting.RentingDAO;

/**
 *
 * @author thinh
 */
public class SearchShoppingHistoryAction {
    private String carName;
    private String orderDate;
    private List<RentDTO> listSearchShoppingHistory;
    private final String SUCCESS = "success";
    public SearchShoppingHistoryAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountDTO = (AccountDTO) session.get("USER");
        RentingDAO rentingDAO = new RentingDAO();
        if(orderDate.equals("")) {
            listSearchShoppingHistory = rentingDAO.getListRentByCarName(carName , accountDTO.getEmail());
        } else {
            listSearchShoppingHistory = rentingDAO.getListRentByCarNameAndOrderDate(carName, accountDTO.getEmail(), orderDate +" 00:00:00.000", orderDate +" 23:59:59.998");
        }
        System.out.println("list size :" + listSearchShoppingHistory.size());
        return SUCCESS;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<RentDTO> getListSearchShoppingHistory() {
        return listSearchShoppingHistory;
    }

    public void setListSearchShoppingHistory(List<RentDTO> listSearchShoppingHistory) {
        this.listSearchShoppingHistory = listSearchShoppingHistory;
    }
    
}
