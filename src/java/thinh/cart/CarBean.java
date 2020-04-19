/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.cart;

/**
 *
 * @author thinh
 */
public class CarBean {
    private int carId;
    private String carName;
    private double price;
    private String color;
    private String dateFrom;
    private String dateTo;
    private String category;

    public CarBean(int carId, String carName, double price, String color, String dateFrom, String dateTo, String category) {
        this.carId = carId;
        this.carName = carName;
        this.price = price;
        this.color = color;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.category = category;
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
    
   

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CarBean other = (CarBean) obj;
        if (other.getCarId() != this.carId || !other.getDateFrom().equals(this.dateFrom) || !other.getDateTo().equals(this.dateTo))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 67;
        int result = 5;
        result = prime * result + this.getCarId();  
        return result;
    }

    
    
}
