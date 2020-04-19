/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.renting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinh
 */
public class RentingDAO implements Serializable{
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
    
    public List<RentingDTO> findMax() throws SQLException, ClassNotFoundException {
        List<RentingDTO> listRenting = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarName, M.total " +
"from dbo.Car, (Select CarId, Sum(TotalMoney)as total from dbo.Rent where Status ='active' group by CarId having Sum(TotalMoney) >= All(Select Sum(TotalMoney) from dbo.Rent where Status ='active' group by CarId)) as M " +
"where Car.CarId = M.CarId";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (listRenting == null) {
                listRenting = new ArrayList<>();
            }
            while (rs.next()) {
                String carName = rs.getString("CarName");
                double totalMoney = rs.getDouble("total");
                RentingDTO dto = new RentingDTO(0, carName, 0, 0, totalMoney, null,null, null, null);
                listRenting.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRenting;
    }
    
    public List<RentingDTO> getAll() throws SQLException, ClassNotFoundException {
        List<RentingDTO> listRenting = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select RentId, CarName, Amount, Price, TotalMoney, Email, DateFrom, DateTo, DiscountCode "
                    + "From dbo.Rent, dbo.Car "
                    + "Where dbo.Car.CarId = dbo.Rent.CarId ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (listRenting == null) {
                listRenting = new ArrayList<>();
            }
            while (rs.next()) {
                int rentId = rs.getInt("RentId");
                String carName = rs.getString("CarName");
                int amount = rs.getInt("Price");
                double price = rs.getDouble("Price");
                double totalMoney = rs.getDouble("TotalMoney");
                String email = rs.getString("Email");
                Date datefrom = rs.getDate("DateFrom");
                Date dateTo = rs.getDate("DateTo");
                String discountCode = rs.getString("DiscountCode")==null ? "": rs.getString("DiscountCode");
                RentingDTO dto = new RentingDTO(rentId, carName, amount, price, totalMoney, email, datefrom, dateTo, discountCode);
                listRenting.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRenting;
    }
    
    public List<RentDTO> getListRentByCarNameAndOrderDate(String carNameString,String emailSearch, String orderStartTime, String orderEndTime) throws SQLException, ClassNotFoundException {
        List<RentDTO> listRenting = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select RentId, CarName, Amount, dbo.Rent.Price, TotalMoney, Email, dbo.Rent.DateFrom, dbo.Rent.DateTo, PercentDiscount,dbo.Rent.Status, TransactionId "
                    + "From dbo.Rent, dbo.Car, dbo.Discounts "
                    + "Where Email = ? and dbo.Car.CarId = dbo.Rent.CarId and dbo.Discounts.Code = Rent.DiscountCode and CarName like ? and TransactionId in (Select TransactionId from TransactionTable "
                                                                                                    + " Where Time between ? and ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, emailSearch);
            ps.setString(2, "%"+ carNameString+"%");
            ps.setString(3, orderStartTime);
            ps.setString(4, orderEndTime);
            rs = ps.executeQuery();
            if (listRenting == null) {
                listRenting = new ArrayList<>();
            }
            while (rs.next()) {
                int rentId = rs.getInt("RentId");
                String carName = rs.getString("CarName");
                int amount = rs.getInt("Price");
                double price = rs.getDouble("Price");
                double totalMoney = rs.getDouble("TotalMoney");
                String email = rs.getString("Email");
                String datefrom = rs.getString("DateFrom");
                String dateTo = rs.getString("DateTo");
                double percentDiscount = rs.getDouble("PercentDiscount");
                String status = rs.getString("Status");
                int transactionId = rs.getInt("TransactionId");
                RentDTO dto = new RentDTO(rentId, carName, amount, price, totalMoney, email, datefrom, dateTo, percentDiscount, status, transactionId);
                listRenting.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRenting;
    }
    public List<RentDTO> getListRentByCarName(String carNameString, String emailSearch) throws SQLException, ClassNotFoundException {
        List<RentDTO> listRenting = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select RentId, CarName, Amount, dbo.Rent.Price, TotalMoney, Email, dbo.Rent.DateFrom, dbo.Rent.DateTo, PercentDiscount,dbo.Rent.Status, TransactionId "
                    + "From dbo.Rent, dbo.Car, dbo.Discounts "
                    + "Where dbo.Car.CarId = dbo.Rent.CarId and dbo.Discounts.Code = Rent.DiscountCode and CarName like ? and Email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+ carNameString+"%");
            ps.setString(2, emailSearch);
            rs = ps.executeQuery();
            if (listRenting == null) {
                listRenting = new ArrayList<>();
            }
            while (rs.next()) {
                int rentId = rs.getInt("RentId");
                String carName = rs.getString("CarName");
                int amount = rs.getInt("Price");
                double price = rs.getDouble("Price");
                double totalMoney = rs.getDouble("TotalMoney");
                String email = rs.getString("Email");
                String datefrom = rs.getString("DateFrom");
                String dateTo = rs.getString("DateTo");
                double percentDiscount = rs.getDouble("PercentDiscount");
                String status = rs.getString("Status");
                int transactionId = rs.getInt("TransactionId");
                RentDTO dto = new RentDTO(rentId, carName, amount, price, totalMoney, email, datefrom, dateTo, percentDiscount, status, transactionId);
                listRenting.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRenting;
    }
    public AvailableRentDTO getAvailableCarByCarId(int carId, String dateFrom, String dateTo) throws SQLException, ClassNotFoundException {
        AvailableRentDTO dto = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, SUM(Amount) as SumAmount "
                    + "From dbo.Rent "
                    + "Where CarId = ? and ( (? between DateFrom and DateTo) or (? between DateFrom and DateTo) or (? <= DateFrom and ? >= DateTo)) and Status ='active' "
                    + "Group by CarId";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);
            ps.setString(4, dateFrom);
            ps.setString(5, dateTo);
            rs = ps.executeQuery();
            if (rs.next()) {
                int amount = rs.getInt("SumAmount");
                dto = new AvailableRentDTO(carId, amount);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    public AvailableRentDTO getQuantityCarByCarId(int carId) throws SQLException, ClassNotFoundException {
        AvailableRentDTO dto = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, Quantity "
                    + "From dbo.Car "
                    + "Where CarId = ?"; 
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int amount = rs.getInt("Quantity");
                dto = new AvailableRentDTO(carId, amount);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public int getTransactionIdByRentId(int rentId) throws SQLException, ClassNotFoundException {
        AvailableRentDTO dto = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select TransactionId "
                    + "From dbo.Rent "
                    + "Where RentId = ?"; 
            ps = con.prepareStatement(sql);
            ps.setInt(1, rentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TransactionId");
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
    
    public List<AvailableRentDTO> getAvailableCar(String dateFrom, String dateTo) throws SQLException, ClassNotFoundException {
        List<AvailableRentDTO> list = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, SUM(Amount) as SumAmount "
                    + "From dbo.Rent "
                    + "Where (? between DateFrom and DateTo) or (? between DateFrom and DateTo) "
                    + "Group by CarId";
            ps = con.prepareStatement(sql);
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);
            rs = ps.executeQuery();
            if (list == null) {
                list = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                int sumAmount = rs.getInt("SumAmount");
                AvailableRentDTO dto = new AvailableRentDTO(carId, sumAmount);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    public List<RentDTO> getListShoppingHistory(String email) throws SQLException, ClassNotFoundException {
        List<RentDTO> list = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select RentId, CarName, Amount, Rent.Price, TotalMoney, Email, Rent.DateFrom, Rent.DateTo, PercentDiscount, Rent.Status, TransactionId"
                    + " from Rent, Car, Discounts "
                    + " where Email = ? and Rent.CarId = Car.CarId and Discounts.Code = Rent.DiscountCode";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (list == null) {
                list = new ArrayList<>();
            }
            while (rs.next()) {
                int id = rs.getInt("RentId");
                String carName = rs.getString("CarName");
                int amount = rs.getInt("Amount");
                double price = rs.getDouble("Price");
                double totalMoney = rs.getDouble("TotalMoney");
                String dateFrom = rs.getString("DateFrom");
                String dateTo = rs.getString("dateTo");
                double percentDiscount = rs.getDouble("PercentDiscount");
                String status = rs.getString("Status");
                int transactionId = rs.getInt("TransactionId");
                RentDTO dto = new RentDTO(id, carName, amount, price, totalMoney, email, dateFrom, dateTo, percentDiscount, status, transactionId);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean insertRent(int carId, int amount, double price, double totalMoney, String email, String dateFrom, String dateTo, String discountCode, String status , int transactionId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Insert into Rent (CarId,Amount, Price, TotalMoney, Email, DateFrom, DateTo, DiscountCode, Status, TransactionId ) "
                    + " values (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            ps.setInt(2, amount);
            ps.setDouble(3, price);
            ps.setDouble(4, totalMoney);
            ps.setString(5, email);
            ps.setString(6, dateFrom);
            ps.setString(7,dateTo);
            ps.setString(8, discountCode);
            ps.setString(9, status);
            ps.setInt(10, transactionId);
            int row = ps.executeUpdate();
            if (row >0 ) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean cancelRent(int rentId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Update dbo.Rent set Status ='inactive' where RentId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, rentId);
            int row = ps.executeUpdate();
            if (row >0 ) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
