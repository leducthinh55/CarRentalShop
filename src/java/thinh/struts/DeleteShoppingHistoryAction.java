/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import thinh.discount.DiscountDAO;
import thinh.renting.RentingDAO;
import thinh.transaction.TransactionDAO;

/**
 *
 * @author thinh
 */
public class DeleteShoppingHistoryAction {
    private int rentId;
    private String dateFrom;
    private String errorDate;
    private String status;
    private double amountOfMoney;
    private final String INPUT ="input";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    public DeleteShoppingHistoryAction() {
    }
    
    public String execute() throws Exception {
        System.out.println("status : " + status);
        if (status.equals("inactive")) {
            return SUCCESS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = sdf.parse(LocalDate.now().toString()) ;
        Date dateStart = sdf.parse(dateFrom);
        if (curDate.after(dateStart)) {
            errorDate = "You can not cancel it !";
            return INPUT;
        }
        RentingDAO rentingDAO = new RentingDAO();
        boolean check = rentingDAO.cancelRent(rentId);
        if (check) {
            int transactionId = rentingDAO.getTransactionIdByRentId(rentId);
            TransactionDAO transactionDAO = new TransactionDAO();
            String code = transactionDAO.getDiscountCodeById(transactionId);
            DiscountDAO discountDAO = new DiscountDAO();
            double percentDiscount = discountDAO.getPercentDiscountByCode(code);
            double totalMoneyNet = transactionDAO.getTotalMoneyById(transactionId);
            double totalMoneyAfterDelete = totalMoneyNet - amountOfMoney;
            double totalMoneyDiscountAterDelete = totalMoneyAfterDelete - totalMoneyAfterDelete* percentDiscount;
            boolean check2 = transactionDAO.changeTableAfterDeleteShoppingHistory(transactionId, totalMoneyAfterDelete, totalMoneyDiscountAterDelete);
            if (check2) return SUCCESS;
        }
        return FAIL;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
