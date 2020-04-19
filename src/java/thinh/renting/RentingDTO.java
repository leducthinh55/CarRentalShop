/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.renting;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author thinh
 */
public class RentingDTO implements Serializable{
    private int rentId;
    private String carName;
    private int amount;
    private double price;
    private double totalMoney;
    private String email;
    private Date dateFrom;
    private Date dateTo;
    private String discountCode;

    public RentingDTO(int rentId, String carName, int amount, double price, double totalMoney, String email, Date dateFrom, Date dateTo) {
        this.rentId = rentId;
        this.carName = carName;
        this.amount = amount;
        this.price = price;
        this.totalMoney = totalMoney;
        this.email = email;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public RentingDTO(int rentId, String carName, int amount, double price, double totalMoney, String email, Date dateFrom, Date dateTo, String discountCode) {
        this.rentId = rentId;
        this.carName = carName;
        this.amount = amount;
        this.price = price;
        this.totalMoney = totalMoney;
        this.email = email;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.discountCode = discountCode;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    
    
}
