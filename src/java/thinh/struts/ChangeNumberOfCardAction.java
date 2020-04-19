/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import thinh.caculate.CaculateAmountOfMoney;
import thinh.cart.CarBean;
import thinh.cart.Cart;

/**
 *
 * @author thinh
 */
public class ChangeNumberOfCardAction {

    private int carIdChange;
    private String dateFromChange;
    private String dateToChange;
    private String changeStatus;
    private final String SUCCESS = "success";

    public ChangeNumberOfCardAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        CarBean carBean = new CarBean(carIdChange, "", 0, "", dateFromChange, dateToChange, "");
        cart.changeNumberInCart(carBean, changeStatus);
        session.put("CART", cart);
        double amountOfMoney = 0;
        Map<CarBean, Integer> items = cart.getItems();
        if (items != null) {
            for (CarBean bean : items.keySet()) {
                int quantity = items.get(bean);
                double price = bean.getPrice();
                String startDate = bean.getDateFrom();
                String endDate = bean.getDateTo();
                if (!(startDate.equals("") && endDate.equals(""))) {
                    amountOfMoney += CaculateAmountOfMoney.caculateAmountOfMoneyOfDateFromAndDateTo(startDate, endDate, price) * quantity;
                } else {
                    amountOfMoney += bean.getPrice() * quantity;
                }
            }
            session.put("AMOUNT_OF_MONEY", amountOfMoney);
        } 
        return SUCCESS;

    }

    public int getCarIdChange() {
        return carIdChange;
    }

    public void setCarIdChange(int carIdChange) {
        this.carIdChange = carIdChange;
    }

    public String getDateFromChange() {
        return dateFromChange;
    }

    public void setDateFromChange(String dateFromChange) {
        this.dateFromChange = dateFromChange;
    }

    public String getDateToChange() {
        return dateToChange;
    }

    public void setDateToChange(String dateToChange) {
        this.dateToChange = dateToChange;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

}
