/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.accounts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author thinh
 */
public class AccountDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    public AccountDTO getUser(String email, String password) throws SQLException, ClassNotFoundException{
        //1. Open Connection
        try {
            
            con = thinh.utils.DBUtiles.makeConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select Email, Password,Phone, Name,Address, CreateDate, Role, Status "
                        + "From dbo.Accounts "
                        + "Where Email = ? and Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                AccountDTO account = null;
                if (rs.next()) {
                    String Email = rs.getString("Email").trim();
                    String Password = rs.getString("Password").trim();
                    String Phone = rs.getString("Phone").trim();
                    String Name = rs.getNString("Name").trim();
                    String Address = rs.getNString("Address").trim();
                    Timestamp CreateDate = rs.getTimestamp("CreateDate");
                    String Role = rs.getNString("Role");
                    String Status = rs.getNString("Status");
                    account = new AccountDTO(Email, Password, Phone, Name, Address, CreateDate, Role, Status);
                }
                return account;
            } // en if con exist
        } finally {
            closeConnection();
        }
        return null;
    }
    
    public AccountDTO getUserForGoogle(String email) throws SQLException, ClassNotFoundException{
        //1. Open Connection
        try {
            
            con = thinh.utils.DBUtiles.makeConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select Email, Password,Phone, Name,Address, CreateDate, Role, Status "
                        + "From dbo.Accounts "
                        + "Where Email = ? and Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                AccountDTO account = null;
                if (rs.next()) {
                    String Email = rs.getString("Email").trim();
                    String Password = rs.getString("Password").trim();
                    String Phone = rs.getString("Phone").trim();
                    String Name = rs.getNString("FullName").trim();
                    String Address = rs.getNString("Address").trim();
                    Timestamp CreateDate = rs.getTimestamp("CreateDate");
                    String Role = rs.getNString("Role");
                    String Status = rs.getNString("Status");
                    account = new AccountDTO(Email, Password, Phone, Name, Address, CreateDate, Role, Status);
                }
                return account;
            } // en if con exist
        } finally {
            closeConnection();
        }
        return null;
    }
    
    
    public boolean createAccount(String email, String password, String phone, String name,String address, Timestamp createDate, String role, String status) throws SQLException, ClassNotFoundException {
        //1. Open Connection
        boolean check = false;
        try {
            con = thinh.utils.DBUtiles.makeConnection();

            if (con != null) {
                //2. Create SQL string
                String sql = "Insert into dbo.Accounts ( Email, Password,Phone, Name,Address, CreateDate, Role, Status) values (?,?,?,?,?,?,?,?)";
                //3. Create statement and set value to parameter (?) vi tri tinh tu so 1
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, phone);
                stm.setString(4, address);
                stm.setString(5, address);
                stm.setTimestamp(6, createDate);
                stm.setString(7,role);
                stm.setString(8, status);
                //4. Excute Query
                int row = stm.executeUpdate();
                //5. Process result

                if (row > 0) { //Result set se tra ve 0 neu ko co trong database
                    check = true;
                }
            } // en if con exist
        } finally {
            closeConnection();
        }
        return check;
    }

}
