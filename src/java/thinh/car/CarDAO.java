/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinh.car;

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
public class CarDAO implements Serializable {

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

    public CarDTO getCarById(int carId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where dbo.Car.CategoryId = dbo.Category.CategoryId and CarId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int CarId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(CarId, carName, color, year, categoryName,image, price, quantity, status);
                return dto;
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    public List<CarDTO> getCarCannotSell(String dateFrom, String dateTo) throws SQLException, ClassNotFoundException {
        List<CarDTO> list = new ArrayList<>();
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, Image, Price, Quantity, Status\n" +
"From Car \n" +
"where CarId not in (Select CarId from dbo.Rent where ((? between DateFrom and DateTo) OR (? between DateFrom and DateTo)\n" +
"OR (? <= DateFrom and ? >= DateTo)) and Status = 'active')";
            ps = con.prepareStatement(sql);
            ps.setString(1, dateFrom);
            ps.setString(2, dateTo);
            ps.setString(3, dateFrom);
            ps.setString(4, dateTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                int CarId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = "";
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(CarId, carName, color, year, categoryName,image, price, quantity, status);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    public int getQuantityOfCarActive(int carId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select Quantity "
                    + "From dbo.Car "
                    + "Where CarId = ? and Status ='active'";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("Quantity");
                return quantity;
            }
        } finally {
            closeConnection();
        }
        return -1;
    }
    public List<CarDTO> searchAll() throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where dbo.Car.CategoryId = dbo.Category.CategoryId ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName,image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public List<CarDTO> searchBaseOnNameAndStatus(String nameSearch, String statusSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName,Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId and Status = ?  ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, statusSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName,image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnName(String nameSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName,Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName,image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnNameAndStatusAndAmount(String nameSearch, String statusSearch, int amountSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName,Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId and Status = ? and Quantity >= ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, statusSearch);
            ps.setInt(3, amountSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName,image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public List<CarDTO> searchBaseOnNameAndCategoryAndStatus(String nameSearch, String categorySearch, String statusSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId and dbo.Category.CategoryName = ? and Status = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, categorySearch);
            ps.setString(3, statusSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName, image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnNameAndCategory(String nameSearch, String categorySearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId and dbo.Category.CategoryName = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, categorySearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName, image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnNameAndCategoryAndStatusAndAmount(String nameSearch, String categorySearch, String statusSearch , int amountOfSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status "
                    + "From dbo.Car, dbo.Category "
                    + "Where CarName like ? and dbo.Car.CategoryId = dbo.Category.CategoryId and dbo.Category.CategoryName = ? and Status = ? and Quantity >= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, categorySearch);
            ps.setString(3, statusSearch);
            ps.setInt(4, amountOfSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName, image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnNameAndFromDateAndToDateAndAmount(String nameSearch, String dateFromSearch, String dateToSearch, int amountSearch, String statusSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status " +
                        " From Car, Category " +
                        " Where CarName like ? and Status = ? and Category.CategoryId = Car.CategoryId and Quantity >= ? and CarId not in (Select CarId from Rent" +
                                                                                                                                        " where ((? between DateFrom and DateTo) or (? between DateFrom and DateTo) or (? <= DateFrom and ? >= DateTo)) and Status = 'active' " +
                                                                                                                                        " group by CarId" +
                                                                                                                                        " having (select Quantity from Car where Car.CarId = Rent.CarId) - SUM(Amount) - ? < 0 )"
                    + " Order by Price ASC";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, statusSearch);
            ps.setInt(3, amountSearch);
            ps.setString(4, dateFromSearch);
            ps.setString(5, dateToSearch);
            ps.setString(6, dateFromSearch);
            ps.setString(7, dateToSearch);
            ps.setInt(8, amountSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName, image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }
    public List<CarDTO> searchBaseOnNameAndCategoryAndFromDateAndToDateAndAmount(String nameSearch, String categorySearch,String dateFromSearch, String dateToSearch, int amountSearch, String statusSearch) throws SQLException, ClassNotFoundException {
        List<CarDTO> listCar = null;
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Select CarId, CarName, Color, Year, CategoryName, Image, Price, Quantity, Status " +
                        " From Car, Category " +
                        " Where CarName like ? and Status = ? and CategoryName = ? and Quantity > ? and Category.CategoryId = Car.CategoryId and CarId not in (Select CarId from Rent" +
                                                                                                                                " where ((? between DateFrom and DateTo) or (? between DateFrom and DateTo) or (? <= DateFrom and ? >= DateTo)) and Status = 'active' " +
                                                                                                                                " group by CarId" +
                                                                                                                                " having (select Quantity from Car where Car.CarId = Rent.CarId) - SUM(Amount) - ? < 0 )";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, statusSearch);
            ps.setString(3, categorySearch);
            ps.setInt(4, amountSearch);
            ps.setString(5, dateFromSearch);
            ps.setString(6, dateToSearch);
            ps.setString(7, dateFromSearch);
            ps.setString(8, dateToSearch);
            ps.setInt(9, amountSearch);
            rs = ps.executeQuery();
            if (listCar == null) {
                listCar = new ArrayList<>();
            }
            while (rs.next()) {
                int carId = rs.getInt("CarId");
                String carName = rs.getString("CarName");
                String color = rs.getString("Color");
                Date year = rs.getDate("Year");
                String categoryName = rs.getNString("CategoryName");
                String image = rs.getString("Image");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                CarDTO dto = new CarDTO(carId, carName, color, year, categoryName, image, price, quantity, status);
                listCar.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public boolean addCar(String carName, String color, String year, int categoryId, String image,double price, int quantity, String status) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Insert into dbo.Car (CarName, Color, Year, CategoryId , Image, Price, Quantity, Status) values (?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, carName);
            ps.setString(2, color);
            ps.setString(3, year);
            ps.setInt(4, categoryId);
            ps.setString(5, image);
            ps.setDouble(6, price);
            ps.setInt(7, quantity);
            ps.setString(8, status);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean updateCar(int carId,String carName, String color, int categoryId,String image, double price, int quantity, String status) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Update dbo.Car set CarName = ? , Color =?, CategoryId =? ,Image=?, Price =?, Quantity =?, Status =?"
                    + " where carId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carName);
            ps.setString(2, color);
            ps.setInt(3, categoryId);
            ps.setString(4, image);
            ps.setDouble(5, price);
            ps.setInt(6, quantity);
            ps.setString(7, status);
            ps.setInt(8, carId);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean deleteCar(int carId) throws SQLException, ClassNotFoundException {
        try {
            con = thinh.utils.DBUtiles.makeConnection();
            String sql = "Update dbo.Car set Status = 'inactive' "
                    + " where carId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
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
