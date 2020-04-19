/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import thinh.caculate.CaculateAmountOfMoney;
import thinh.cart.CarBean;
import thinh.cart.Cart;

/**
 *
 * @author thinh
 */
public class UpdateCartDateFromAndDateToAction {

    private final String SUCCESS = "success";
    private final String INPUT = "input";
    private String oldDateFrom;
    private String oldDateTo;
    private String newDateFrom;
    private String newDateTo;
    private int carId;
    private String errorDate;

    public UpdateCartDateFromAndDateToAction() {
    }

    public String execute() throws Exception {
        System.out.println("new Date from : " + newDateFrom);
        System.out.println("old Date To : " + oldDateTo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (newDateFrom != null && oldDateTo != null) {
            if (!newDateFrom.equals("") && !oldDateTo.equals("")) {
                Date dateFrom = sdf.parse(newDateFrom);
                Date dateTo = sdf.parse(oldDateTo);
                if (dateFrom.compareTo(dateTo) > 0) {
                    errorDate = "Rental date can not greater than return date";
                    return INPUT;
                }
            }
        }
        if (oldDateFrom != null && newDateTo != null) {
            if (!oldDateFrom.equals("") && !newDateTo.equals("")) {
                Date dateFrom = sdf.parse(oldDateFrom);
                Date dateTo = sdf.parse(newDateTo);
                if (dateFrom.compareTo(dateTo) > 0) {
                    errorDate = "Rental date can not greater than return date";
                    return INPUT;
                }
            }
        }
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        Map<CarBean, Integer> items = cart.getItems();
        if (items != null) {
            for (CarBean bean : items.keySet()) { // set date from and date to again after receive from function onchange in cartPage.jsp
                if (bean.getCarId() == carId && bean.getDateFrom().equals(oldDateFrom) && bean.getDateTo().equals(oldDateTo)) {
                    if (newDateTo == null) {
                        int checkQuantity = 0;
                        for (CarBean beanCheck : items.keySet()) {
                            if (beanCheck.getCarId() == carId && beanCheck.getDateFrom().equals(newDateFrom) && beanCheck.getDateTo().equals(oldDateTo)) {
                                // sitution: when change date from and date to, we have 2 bean duplicate
                                checkQuantity = items.get(beanCheck);
                                items.remove(beanCheck);
                                break;
                            }
                        }
                        bean.setDateFrom(newDateFrom);
                        int quantity = items.get(bean);
                        items.replace(bean, quantity, quantity + checkQuantity);
                    } else if (newDateFrom == null) {
                        int checkQuantity = 0;
                        for (CarBean beanCheck : items.keySet()) {
                            if (beanCheck.getCarId() == carId && beanCheck.getDateFrom().equals(oldDateFrom) && beanCheck.getDateTo().equals(newDateTo)) {
                                // sitution: when change date from and date to, we have 2 bean duplicate
                                checkQuantity = items.get(beanCheck);
                                items.remove(beanCheck);
                                break;
                            }
                        }

                        bean.setDateTo(newDateTo);
                        int quantity = items.get(bean);
                        items.replace(bean, quantity, quantity + checkQuantity);
                    }
                    break;
                }
            }
        }
        double amountOfMoney = 0;
        for (CarBean bean : items.keySet()) {
            int  quantity = items.get(bean);
            double price = bean.getPrice();
            String startDate = bean.getDateFrom();
            String endDate = bean.getDateTo();
            if (!(startDate.equals("") || endDate.equals(""))) {
                amountOfMoney += CaculateAmountOfMoney.caculateAmountOfMoneyOfDateFromAndDateTo(startDate, endDate, price)* quantity;
            } else {
                amountOfMoney += bean.getPrice() * quantity;
            }
        }
        session.put("AMOUNT_OF_MONEY", amountOfMoney);
        session.put("CART", cart);
        return SUCCESS;
    }

    public String getOldDateFrom() {
        return oldDateFrom;
    }

    public void setOldDateFrom(String oldDateFrom) {
        this.oldDateFrom = oldDateFrom;
    }

    public String getOldDateTo() {
        return oldDateTo;
    }

    public void setOldDateTo(String oldDateTo) {
        this.oldDateTo = oldDateTo;
    }

    public String getNewDateFrom() {
        return newDateFrom;
    }

    public void setNewDateFrom(String newDateFrom) {
        this.newDateFrom = newDateFrom;
    }

    public String getNewDateTo() {
        return newDateTo;
    }

    public void setNewDateTo(String newDateTo) {
        this.newDateTo = newDateTo;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setError(String errorDate) {
        this.errorDate = errorDate;
    }

}
