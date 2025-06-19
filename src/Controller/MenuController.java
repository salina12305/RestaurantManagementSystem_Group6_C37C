/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.OrderDAO;
import Database.DBConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author aiden
 */
public class MenuController {
    
   

    public static void handleCreate(String id, String name, String count, String price, String empName) {
        if (id.isEmpty() || name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemId = Integer.parseInt(id);
            int itemCount = Integer.parseInt(count);
            double itemPrice = Double.parseDouble(price);
            Connection conn = (Connection) DBConnection.getConnection();
            boolean success = OrderDAO.createOrder((java.sql.Connection) conn, itemId, name, itemCount, itemPrice, empName);
            if (success) {
                JOptionPane.showMessageDialog(null, "Order created successfully!");
                new NextForm().setVisible(true);  // replace with your actual next interface
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating order: " + e.getMessage());
        }
    }

    public static void handleUpdate(String id, String name, String count, String price, String empName) {
        if (id.isEmpty() || name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemId = Integer.parseInt(id);
            int itemCount = Integer.parseInt(count);
            double itemPrice = Double.parseDouble(price);
            Connection conn = (Connection) DBConnection.getConnection();
            boolean success = OrderDAO.updateOrder((java.sql.Connection) conn, itemId, name, itemCount, itemPrice, empName);
            if (success) {
                JOptionPane.showMessageDialog(null, "Order updated successfully!");
                new NextForm().setVisible(true);
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating order: " + e.getMessage());
        }
    }

    public static void handleDelete(String id) {
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Item ID!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemId = Integer.parseInt(id);
            Connection conn = (Connection) DBConnection.getConnection();
            boolean success = OrderDAO.deleteOrder((java.sql.Connection) conn, itemId);
            if (success) {
                JOptionPane.showMessageDialog(null, "Order deleted successfully!");
                new NextForm().setVisible(true);
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting order: " + e.getMessage());
        }
    }

    public static void create(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void update(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void deleteOrder(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void delete(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
