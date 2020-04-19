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
public class AddToCartAction {
    
    private final String SUCCESS = "success";
    private final String SEARCH = "search";
    private int carId;
    private String carName;
    private double price;
    private String color;
    private String dateFrom;
    private String dateTo;
    private String category;
    private String carNameSearch;
    private int amountSearch;
    private String categorySearch;
    public AddToCartAction() {
    }
    
    public String execute() throws Exception {
        CarBean carBean = new CarBean(carId, carName, price, color, dateFrom, dateTo, category);
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addToCart(carBean);
        session.put("CART", cart);
        Map<CarBean,Integer> items = cart.getItems();
        double amountOfMoney = 0;
        for (CarBean bean : items.keySet()) {
            int  quantity = items.get(bean);
            double price = bean.getPrice();
            String startDate = bean.getDateFrom();
            String endDate = bean.getDateTo();
            if (!(startDate.equals("") && endDate.equals(""))) {
                 amountOfMoney += CaculateAmountOfMoney.caculateAmountOfMoneyOfDateFromAndDateTo(startDate, endDate, price)* quantity;
            } else {
                amountOfMoney += bean.getPrice() * quantity;
            }
        }
        session.put("AMOUNT_OF_MONEY", amountOfMoney);
        if (!(carNameSearch.equals("") && categorySearch.equals("") && dateFrom.equals("") && dateTo.equals("") && amountSearch == 1)){
            return SEARCH;
        }
        return SUCCESS;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCarNameSearch() {
        return carNameSearch;
    }

    public void setCarNameSearch(String carNameSearch) {
        this.carNameSearch = carNameSearch;
    }

    public int getAmountSearch() {
        return amountSearch;
    }

    public void setAmountSearch(int amountSearch) {
        this.amountSearch = amountSearch;
    }


    public String getCategorySearch() {
        return categorySearch;
    }

    public void setCategorySearch(String categorySearch) {
        this.categorySearch = categorySearch;
    }
    
}
