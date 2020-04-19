/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.feedbacks;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinh
 */
public class FeedbackDAO implements Serializable{
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
    public List<FeedbackDTO> getListFeedbacksByCarId(int carId) throws ClassNotFoundException, SQLException {
        List<FeedbackDTO> list = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select ID, Email, CarId, Rating, Description, TimeFeedback from Feedbacks where carId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            rs = ps.executeQuery();
            if (list == null) {
                list = new ArrayList<>();
            }
            while (rs.next()) {
                int id = rs.getInt("ID");
                String email = rs.getString("Email");
                int CarId = rs.getInt("CarId");
                int rating = rs.getInt("rating");
                String description = rs.getString("Description");
                Timestamp timeFeedback = rs.getTimestamp("TimeFeedback");
                FeedbackDTO  dto = new FeedbackDTO(id, email, CarId, rating, description, timeFeedback);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    public boolean addFeedbackHaveRating(String email, int carId, int rating, String description, Timestamp timeFeedback) throws ClassNotFoundException, SQLException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Insert into Feedbacks (Email, CarId, Rating, Description, TimeFeedback) values (?,?,?,?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, carId);
            ps.setInt(3, rating);
            ps.setString(4, description);
            ps.setTimestamp(5, timeFeedback);
            int row  = ps.executeUpdate();
            if (row >0 ) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
