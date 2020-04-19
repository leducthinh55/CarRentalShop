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
import thinh.cart.CarBean;
import thinh.cart.Cart;
import thinh.renting.RentingDAO;

/**
 *
 * @author thinh
 */
public class GetCartAction {
    private String dateFrom;
    private String dateTo;
    public GetCartAction() {
    }
    
    public String execute() throws Exception {
        RentingDAO rentingDAO = new RentingDAO();
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        if (cart == null) {
            cart = new Cart();
        }
        CarDAO carDAO = new CarDAO();
        List<CarDTO> listCar = carDAO.searchBaseOnNameAndFromDateAndToDateAndAmount("", dateFrom, dateTo, 1, "active");
        System.out.println("list car size : " + listCar.size());
        int i = 0;
        for (CarDTO dto : listCar) {
            if (i == 3) break;
            int carId = dto.getCarId();
            String carName = dto.getCarName();
            double price = dto.getPrice();
            String color = dto.getColor();
            String category = dto.getCategory();
            CarBean bean = new CarBean(carId, carName, price, color, dateFrom, dateTo, category);
            
            cart.addToCart(bean);
            i++;
        }
        session.put("CART", cart);
        return "success";
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
    
}
