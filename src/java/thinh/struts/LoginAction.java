/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import thinh.accounts.AccountDAO;
import thinh.accounts.AccountDTO;

/**
 *
 * @author thinh
 */
public class LoginAction {
    private String username;
    private String password;
    private String error;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    public LoginAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        AccountDAO accountDAO = new AccountDAO();
        AccountDTO accountDTO = accountDAO.getUser(username, password);
        if (accountDTO != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("USER", accountDTO);
            url = SUCCESS;
        } else {
            error = "Username or Password is incorrect !";
        }
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
