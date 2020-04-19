/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author thinh
 */
public class LogOutAction {
    private final String SUCCESS = "success";
    public LogOutAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        if (session != null) {
            session.remove("CART");
            session.remove("USER");
            session.remove("AMOUNT_OF_MONEY");
        }
        return SUCCESS;
    }

}
