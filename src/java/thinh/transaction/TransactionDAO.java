/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.transaction;

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
public class TransactionDAO implements Serializable{
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
    public boolean insertRent(String email, double totalMoneyDiscount, String discountCode, double totalMoneyNet, Timestamp time) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Insert into TransactionTable (Email, TotalMoneyDiscount,DiscountCode, TotalMoneyNet,Time) "
                    + " values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setDouble(2, totalMoneyDiscount);
            ps.setString(3, discountCode);
            ps.setDouble(4, totalMoneyNet);
            ps.setTimestamp(5, time);
            int row = ps.executeUpdate();
            if (row >0 ) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public double getTotalMoneyById(int transactionId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select TotalMoneyNet from TransactionTable where transactionId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, transactionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("TotalMoneyNet");
            }
        } finally {
            closeConnection();
        }
        return -1;
    }
    public boolean changeTableAfterDeleteShoppingHistory(int transactionId, double totalMoneyNet, double totalMoneyDiscount) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Update TransactionTable set TotalMoneyNet =? , TotalMoneyDiscount =? "
                    + " where transactionId = ?";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, totalMoneyNet);
            ps.setDouble(2, totalMoneyDiscount);
            ps.setInt(3, transactionId);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public String getDiscountCodeById(int transactionId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select DiscountCode from TransactionTable where transactionId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, transactionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("DiscountCode");
            }
        } finally {
            closeConnection();
        }
        return "";
    }
    public int getLastID() throws SQLException, ClassNotFoundException {
        int lastID = -1;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "SELECT Max(TransactionID) as LastID FROM TransactionTable";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastID = rs.getInt("LastID");
            }
            return lastID;
        } finally {
            closeConnection();
        }
    }
}
