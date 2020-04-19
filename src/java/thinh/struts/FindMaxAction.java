/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import java.util.List;
import thinh.renting.RentingDAO;
import thinh.renting.RentingDTO;

/**
 *
 * @author thinh
 */
public class FindMaxAction {
    private List<RentingDTO> listRent;
    public FindMaxAction() {
    }
    
    public String execute() throws Exception {
        RentingDAO dao = new RentingDAO();
        listRent = dao.findMax();
        return "success";
    }

    public List<RentingDTO> getListRent() {
        return listRent;
    }

    public void setListRent(List<RentingDTO> listRent) {
        this.listRent = listRent;
    }
    
}
