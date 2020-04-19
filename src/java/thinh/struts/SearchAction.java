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
public class SearchAction {
    private final String SUCCESS = "success";
    private String carNameSearch;
    private String categorySearch;
    private String dateFrom;
    private String dateTo;
    private int amountSearch;
    private List<CarDTO> listSearch;
    public SearchAction() {
    }
    
    public String execute() throws Exception {
        CarDAO carDAO = new CarDAO();
        if (categorySearch.equals("all")) {
            if (!dateFrom.equals("") && !dateTo.equals("")) {
                listSearch = carDAO.searchBaseOnNameAndFromDateAndToDateAndAmount(carNameSearch, dateFrom, dateTo, amountSearch, "active"); 
            }
            else {
                listSearch = carDAO.searchBaseOnNameAndStatusAndAmount(carNameSearch, "active", amountSearch);
            }       
        } else {
            if (!dateFrom.equals("") && !dateTo.equals("")) {
                listSearch = carDAO.searchBaseOnNameAndCategoryAndFromDateAndToDateAndAmount(carNameSearch, categorySearch, dateFrom, dateTo, amountSearch, "active"); 
            }
            else {
                listSearch = carDAO.searchBaseOnNameAndCategoryAndStatusAndAmount(carNameSearch, categorySearch, "active", amountSearch);
            }
        }
        System.out.println("list search: " + listSearch.size());
        return SUCCESS;
    }

    public String getCarNameSearch() {
        return carNameSearch;
    }

    public void setCarNameSearch(String carNameSearch) {
        this.carNameSearch = carNameSearch;
    }

    public String getCategorySearch() {
        return categorySearch;
    }

    public void setCategorySearch(String categorySearch) {
        this.categorySearch = categorySearch;
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

    public int getAmountSearch() {
        return amountSearch;
    }

    public void setAmountSearch(int amountSearch) {
        this.amountSearch = amountSearch;
    }

    public List<CarDTO> getListSearch() {
        return listSearch;
    }

    public void setListSearch(List<CarDTO> listSearch) {
        this.listSearch = listSearch;
    }

    

    
    
}
