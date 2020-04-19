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
import thinh.renting.RentingDAO;
import thinh.renting.RentDTO;

/**
 *
 * @author thinh
 */
public class ShoppingHistoryAction {
    private final String SUCCESS = "success";
    private List<RentDTO> listRent;
    private double amountOfMoneyOfShoppingHistory;
    public ShoppingHistoryAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountDTO = (AccountDTO) session.get("USER");
        if (accountDTO != null) {
            String email = accountDTO.getEmail();
            RentingDAO rentDAO = new RentingDAO();
            listRent = rentDAO.getListShoppingHistory(email);
        }
        return SUCCESS;
    }

    public List<RentDTO> getListRent() {
        return listRent;
    }

    public void setListRent(List<RentDTO> listRent) {
        this.listRent = listRent;
    }

    public double getAmountOfMoneyOfShoppingHistory() {
        return amountOfMoneyOfShoppingHistory;
    }

    public void setAmountOfMoneyOfShoppingHistory(double amountOfMoneyOfShoppingHistory) {
        this.amountOfMoneyOfShoppingHistory = amountOfMoneyOfShoppingHistory;
    }
    
    
}
