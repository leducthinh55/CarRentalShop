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
public class RentDTO implements Serializable{
    private int rentId;
    private String carName;
    private int amount;
    private double price;
    private double totalMoney;
    private String email;
    private String dateFrom;
    private String dateTo;
    private double percentDiscount;
    private String status;
    private int transactionId;

    public RentDTO(int rentId, String carName, int amount, double price, double totalMoney, String email, String dateFrom, String dateTo, double percentDiscount, String status, int transactionId) {
        this.rentId = rentId;
        this.carName = carName;
        this.amount = amount;
        this.price = price;
        this.totalMoney = totalMoney;
        this.email = email;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.percentDiscount = percentDiscount;
        this.status = status;
        this.transactionId = transactionId;
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

    public double getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    
    
}
