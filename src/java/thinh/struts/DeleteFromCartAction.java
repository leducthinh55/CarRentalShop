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
public class DeleteFromCartAction {
    private int carIdDelete;
    private String dateFromDelete;
    private String dateToDelete;
    private final String SUCCESS = "success";
    public DeleteFromCartAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        CarBean carBean = new CarBean(carIdDelete, "", carIdDelete, "", dateFromDelete, dateToDelete, "");
        Map<CarBean, Integer> items = cart.getItems();
        if (items != null ) {
            if (!items.isEmpty()) {
                items.remove(carBean);
            }
        }
        double amountOfMoney = 0;
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
        session.put("CART", cart);
        return SUCCESS;
    }

    public int getCarIdDelete() {
        return carIdDelete;
    }

    public void setCarIdDelete(int carIdDelete) {
        this.carIdDelete = carIdDelete;
    }

    public String getDateFromDelete() {
        return dateFromDelete;
    }

    public void setDateFromDelete(String dateFromDelete) {
        this.dateFromDelete = dateFromDelete;
    }

    public String getDateToDelete() {
        return dateToDelete;
    }

    public void setDateToDelete(String dateToDelete) {
        this.dateToDelete = dateToDelete;
    }

    
    
}
