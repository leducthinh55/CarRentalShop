/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import thinh.accounts.AccountDTO;
import thinh.caculate.CaculateAmountOfMoney;
import thinh.car.CarDAO;
import thinh.cart.CarBean;
import thinh.cart.Cart;
import thinh.discount.DiscountDAO;
import thinh.renting.AvailableRentDTO;
import thinh.renting.RentingDAO;
import thinh.transaction.TransactionDAO;

/**
 *
 * @author thinh
 */
public class CheckOutAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String INPUT = "input";
    LinkedList<String> listItemOutOfStock;
    LinkedList<String> listItemNotAvailable;
    private String errorDiscount;
    private String errorDate;
    private String email;
    private String discountCode;

    public CheckOutAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        Cart cart = (Cart) session.get("CART");
        Map<CarBean, Integer> items = cart.getItems();
        // check return date must be greater or equal than retal date
        for (CarBean bean : items.keySet()) {
            if (bean.getDateFrom().equals("") || bean.getDateTo().equals("")) {
                errorDate = "You need to input all rental date and return date !";
                return INPUT;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dateFrom = sdf.parse(bean.getDateFrom());
                    Date dateTo = sdf.parse(bean.getDateTo());
                    if (dateFrom.compareTo(dateTo) > 0) { // if dateFrom after dateTo
                        errorDate = "You need to input all rental date less than or equal return date !";
                        return INPUT;
                    } else {
                        Date curDate = sdf.parse(LocalDate.now().toString());
                        if (curDate.compareTo(dateFrom) > 0) { // if today is after date from
                            errorDate = "You need to input all rental date equal or after today !";
                            return INPUT;
                        }
                    }
                } catch (Exception ex) {
                    errorDate = "Your date is not valid";
                    return INPUT;
                }
            }
        }

        // check car in cart is not available or out of stock
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RentingDAO rentingDAO = new RentingDAO();
        boolean check = true;
        CarDAO carDAO = new CarDAO();
        listItemOutOfStock = new LinkedList<>();
        listItemNotAvailable = new LinkedList<>();
        for (CarBean bean : items.keySet()) {
            AvailableRentDTO availableRentDTO = rentingDAO.getAvailableCarByCarId(bean.getCarId(), bean.getDateFrom(), bean.getDateTo());
            int quantityOfCar = carDAO.getQuantityOfCarActive(bean.getCarId());
            if (quantityOfCar == -1) {
                listItemNotAvailable.add(bean.getCarName() + " is not available");
                check = false;
            } else {
                int rentQuantity = items.get(bean);
                if (availableRentDTO == null) {
                    if (rentQuantity > quantityOfCar) {
                        listItemOutOfStock.add(bean.getCarName() + " remain " + quantityOfCar + " cars");
                        check = false;
                    }
                } else {
                    int remainQuantity = quantityOfCar - availableRentDTO.getAmount();
                    if (rentQuantity > remainQuantity) {
                        listItemOutOfStock.add(bean.getCarName() + " remain " + remainQuantity + " cars");
                        check = false;
                    } else {
                        for (CarBean tempBean : items.keySet()) {
                            int quantityTemp = 0;
                            if (!bean.equals(tempBean)) {
                                if (bean.getCarId() == tempBean.getCarId()) {
                                    Date dateFromTempBean = sdf.parse(tempBean.getDateFrom());
                                    Date dateToTempBean = sdf.parse(tempBean.getDateTo());
                                    Date dateFromBean = sdf.parse(bean.getDateFrom());
                                    Date dateToBean = sdf.parse(bean.getDateTo());
                                    if ((dateFromBean.compareTo(dateFromTempBean) >= 0 && dateFromBean.compareTo(dateToTempBean) <= 0)
                                            || (dateToBean.compareTo(dateFromTempBean) >= 0 && dateToBean.compareTo(dateToTempBean) <= 0)
                                            || (dateFromBean.compareTo(dateFromTempBean) <= 0 && dateToBean.compareTo(dateToTempBean) >= 0)) {
                                        quantityTemp += items.get(tempBean);
                                    }
                                }
                            }
                            remainQuantity -= quantityTemp;
                            if (remainQuantity < 0) {
                                String str = bean.getCarName() + " remain 0 cars";
                                boolean checkduplicate = false;
                                for (String s : listItemOutOfStock) {
                                    if (s.equals(str)) {
                                        checkduplicate = true;
                                        break;
                                    }
                                }
                                if (!checkduplicate) {
                                    listItemOutOfStock.add(str);
                                }
                                check = false;
                            } else if (rentQuantity > remainQuantity) {
                                String str = bean.getCarName() + " remain " + remainQuantity + " cars";
                                boolean checkduplicate = false;
                                for (String s : listItemOutOfStock) {
                                    if (s.equals(str)) {
                                        checkduplicate = true;
                                        break;
                                    }
                                }
                                if (!checkduplicate) {
                                    listItemOutOfStock.add(str);
                                }
                                check = false;
                            }
                        }
                    }
                }
            }
        }
        if (!check) {
            return INPUT;
        }
        // insert to transactiontable
        double percentDiscount = 0;
        if (discountCode != null) {
            if (!discountCode.equals("")) {
                Timestamp curTime = Timestamp.valueOf(LocalDateTime.now());
                DiscountDAO discountDAO = new DiscountDAO();
                percentDiscount = discountDAO.getPercentDiscount(discountCode, curTime, email);
                if (percentDiscount == 0) {
                    errorDiscount = "Your discount code is not correct or expried !";
                    return INPUT;
                }
            }
        }
        email = ((AccountDTO)session.get("USER")).getEmail();
        if (percentDiscount == 0) {
            discountCode = "";
        }
        TransactionDAO transactionDAO = new TransactionDAO();
        double amountOfMoney = (double) session.get("AMOUNT_OF_MONEY");
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        double amountOfMoneyWithDiscount = amountOfMoney - percentDiscount * amountOfMoney;
        boolean checkTransaction = transactionDAO.insertRent(email, amountOfMoneyWithDiscount, discountCode, amountOfMoney, time);
        if (checkTransaction) {
            int transactionId = transactionDAO.getLastID();
            for (CarBean bean : items.keySet()) {
                int carId = bean.getCarId();
                int quantity = items.get(bean);
                double price = bean.getPrice();

                String dateFrom = bean.getDateFrom();
                String dateTo = bean.getDateTo();
                double totalMoney = quantity * CaculateAmountOfMoney.caculateAmountOfMoneyOfDateFromAndDateTo(dateFrom, dateTo, price);
                boolean checkRenting = rentingDAO.insertRent(carId, quantity, price, totalMoney, email, dateFrom, dateTo, discountCode, "active", transactionId);
                if (!checkRenting) {
                    return FAIL;
                }
            }
        }
        session.remove("CART");
        session.remove("AMOUNT_OF_MONEY");
        return SUCCESS;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }

    public LinkedList<String> getListItemOutOfStock() {
        return listItemOutOfStock;
    }

    public void setListItemOutOfStock(LinkedList<String> listItemOutOfStock) {
        this.listItemOutOfStock = listItemOutOfStock;
    }

    public LinkedList<String> getListItemNotAvailable() {
        return listItemNotAvailable;
    }

    public void setListItemNotAvailable(LinkedList<String> listItemNotAvailable) {
        this.listItemNotAvailable = listItemNotAvailable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getErrorDiscount() {
        return errorDiscount;
    }

    public void setErrorDiscount(String errorDiscount) {
        this.errorDiscount = errorDiscount;
    }

}
