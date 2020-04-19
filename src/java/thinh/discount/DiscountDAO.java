/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author thinh
 */
public class DiscountDAO {
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
    public double getPercentDiscount(String code, Timestamp curTime, String email) throws ClassNotFoundException, SQLException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select PercentDiscount from Discounts where code = ? and ? between DateFrom and DateTo and "
                    + "code not in (Select DiscountCode from Rent where Email = ? )";
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setTimestamp(2, curTime);
            ps.setString(3, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                double percentDiscount = rs.getDouble("PercentDiscount");
                return percentDiscount;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
    
    public double getPercentDiscountByCode(String code) throws ClassNotFoundException, SQLException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select PercentDiscount from Discounts where code = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                double percentDiscount = rs.getDouble("PercentDiscount");
                return percentDiscount;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
}
