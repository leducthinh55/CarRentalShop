/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinh
 */
public class CategoryDAO implements Serializable{
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
    public int getCategoryID(String category) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CategoryID from dbo.Category where CategoryName = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("CategoryID");
            }
            return -1;
        } finally {
            closeConnection();
        }
    }
    
    public List<String> getListCategory() throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CategoryName from dbo.Category";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getNString("CategoryName"));
            }
            return list;
        } finally {
            closeConnection();
        }
    }
    public boolean createCategory(String category) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Insert into dbo.Category (categoryName) values (?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, category);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true; 
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
