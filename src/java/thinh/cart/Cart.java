/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thinh
 */
public class Cart {

    private String customerEmail;
    private Map<CarBean, Integer> items;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Map<CarBean, Integer> getItems() {
        return items;
    }

    public void addToCart(CarBean car) {
        if (items == null) {
            items = new HashMap<>();
        }
        if (items.containsKey(car)) {
            int quantity = items.get(car);
            items.replace(car, quantity, ++quantity);
        } else {
            items.put(car, 1);
        }
    }

    public void changeNumberInCart(CarBean car, String status) {
        if (items == null) {
            return;
        }
        if (items.containsKey(car)) {
            int quantity = items.get(car);
            if (status.equals("down")) {
                if (quantity > 1) {
                    items.replace(car, quantity, --quantity);
                }
            } else if (status.equals("up")) {
                items.replace(car, quantity, ++quantity);
            }

            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
