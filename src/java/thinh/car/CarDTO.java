/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.car;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author thinh
 */
public class CarDTO implements Serializable{
    private int carId;
    private String carName;
    private String color;
    private Date year;
    private String category;
    private String image;
    private double price;
    private int quantity;
    private String status;

    public CarDTO(int carId, String carName, String color, Date year, String category, String image, double price, int quantity, String status) {
        this.carId = carId;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.category = category;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
