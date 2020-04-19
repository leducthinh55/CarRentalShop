/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thinh
 */
public class DBUtiles implements Serializable{
    public static Connection makeConnection() throws SQLException, ClassNotFoundException{
        //load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Create connecttion String
        String url = "jdbc:sqlserver://localhost:1433;databaseName= CarRental";
        //Open Connection
        Connection con = DriverManager.getConnection(url, "sa","123456");
        return con;
    }
}
