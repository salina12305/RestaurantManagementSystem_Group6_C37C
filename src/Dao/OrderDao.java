package Dao;

import Database.MySqlConnection;
import Model.OrderModel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao {
    MySqlConnection mysql = new MySqlConnection();
    
    //to create order
    public void createOrder(OrderModel order) {
        Connection conn = mysql.openConnection();
        System.out.println("createOrder() called");

        String sql = "INSERT INTO customer_order (orderId,itemName, noOfItem, price, customerName, employeeName) VALUES (?,?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getOrderId());
            pstmt.setString(2, order.getItemName());
            pstmt.setInt(3, order.getNoOfItem());
            pstmt.setDouble(4, order.getPrice());
            pstmt.setString(5, order.getCustomerName());
            pstmt.setString(6, order.getEmployeeName());

            int result = pstmt.executeUpdate();
            System.out.println("Rows affected: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    //to check update order
    public boolean updateOrder(OrderModel order) {
    Connection conn = mysql.openConnection();
    String sql = "UPDATE customer_order SET itemName=?, noOfItem=?, price=?, customerName=?, employeeName=? WHERE orderId=?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, order.getItemName());
        pstmt.setInt(2, order.getNoOfItem());
        pstmt.setDouble(3, order.getPrice());
        pstmt.setString(4, order.getCustomerName());
        pstmt.setString(5, order.getEmployeeName());
        pstmt.setInt(6, order.getOrderId());

        int result = pstmt.executeUpdate();
        System.out.println("Rows updated: " + result);
        return result > 0;
    } catch (SQLException ex) {
        Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        mysql.closeConnection(conn);
    }
    return false;
   }

    //to delete order
   public boolean deleteOrder(int orderId) {
    Connection conn = mysql.openConnection();
    String sql = "DELETE FROM customer_order WHERE orderId=?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, orderId);
        int result = pstmt.executeUpdate();
        System.out.println("Rows deleted: " + result);
        return result > 0;
    } catch (SQLException ex) {
        Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        mysql.closeConnection(conn);
    }
    return false;
}
}
