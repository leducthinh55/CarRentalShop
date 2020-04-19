/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.util.List;
import thinh.car.CarDAO;
import thinh.car.CarDTO;

/**
 *
 * @author thinh
 */
public class FindCannotSellAction {
    private String dateFrom;
    private String dateTo;
    private List<CarDTO> listCar;
    public FindCannotSellAction() {
    }
    
    public String execute() throws Exception {
        CarDAO dao = new CarDAO();
        listCar = dao.getCarCannotSell(dateFrom, dateTo);
        System.out.println("list car size :" + listCar.size());
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

    public List<CarDTO> getListCar() {
        return listCar;
    }

    public void setListCar(List<CarDTO> listCar) {
        this.listCar = listCar;
    }
    
    
}
