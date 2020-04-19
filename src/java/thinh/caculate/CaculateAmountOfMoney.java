/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.caculate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thinh
 */
public class CaculateAmountOfMoney {

    public static double caculateAmountOfMoneyOfDateFromAndDateTo(String dateFrom, String dateTo, double price) throws ParseException {
        double amountOfMoney = 0;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!dateFrom.equals("") && !dateTo.equals("")) {
            Date startDate = sdf.parse(dateFrom);
            Date endDate = sdf.parse(dateTo);
            long distanceTime = endDate.getTime() - startDate.getTime();
            long distanceDay = distanceTime / (24 * 60 * 60 * 1000) + 1;
            amountOfMoney = price * distanceDay;
        }
        return amountOfMoney;
    }
}
