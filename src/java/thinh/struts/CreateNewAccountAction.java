/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.struts;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import thinh.accounts.AccountDAO;
import thinh.accounts.AccountDTO;

/**
 *
 * @author thinh
 */
public class CreateNewAccountAction {

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private final String INPUT = "input";
    private String email;
    private String password;
    private String confirm;
    private String phone;
    private String name;
    private String address;
    private String error;

    public CreateNewAccountAction() {
    }

    public String execute() {
        String url = FAIL;
        try {
            AccountDAO accountDAO = new AccountDAO();
            Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
            boolean check = accountDAO.createAccount(email, password, phone, name, address, createDate, "client", "new");
            if (check) {
                Map session = ActionContext.getContext().getSession();
                AccountDTO accountDTO = new AccountDTO(email, password, phone, name, address, createDate, "client", "new");
                session.put("USER", accountDTO);
                url = SUCCESS;
            }
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate")) {
                error = "Email is existed !";
                url = INPUT;
            }
        }
        return url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
