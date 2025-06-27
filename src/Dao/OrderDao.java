package Dao;

import Model.OrderModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao{

    public static boolean createOrder(Connection conn, int itemId, String itemName, int itemCount, double price, String employeeName) throws SQLException {
        String query = "INSERT INTO orders (item_id, item_name, quantity, price, employee_name) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemId);
        ps.setString(2, itemName);
        ps.setInt(3, itemCount);
        ps.setDouble(4, price);
        ps.setString(5, employeeName);
        return ps.executeUpdate() > 0;
    }

    public static boolean updateOrder(Connection conn, int itemId, String itemName, int itemCount, double price, String employeeName) throws SQLException {
        String query = "UPDATE orders SET item_name=?, quantity=?, price=?, employee_name=? WHERE item_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, itemName);
        ps.setInt(2, itemCount);
        ps.setDouble(3, price);
        ps.setString(4, employeeName);
        ps.setInt(5, itemId);
        return ps.executeUpdate() > 0;
    }

    public static boolean deleteOrder(Connection conn, int itemId) throws SQLException {
        String query = "DELETE FROM orders WHERE item_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemId);
        return ps.executeUpdate() > 0;
    }

    public boolean updateOrder(OrderModel order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
