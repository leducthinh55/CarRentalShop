/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.renting;

import java.io.Serializable;

/**
 *
 * @author thinh
 */
public class AvailableRentDTO implements Serializable{
    private int carId;
    private int amount;

    public AvailableRentDTO(int carId, int amount) {
        this.carId = carId;
        this.amount = amount;
    }

    
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    
}
