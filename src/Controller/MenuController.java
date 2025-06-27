/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Dao.MenuDao;
//import Database.DBConnection;
import java.sql.Connection;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Database.MySqlConnection;

public class MenuController {
    
   

    public static void handleCreate(String id, String name, String count, String price, String empName) {
        
        MySqlConnection mysql = new MySqlConnection();
        if (id.isEmpty() || name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemId = Integer.parseInt(id);
            int itemCount = Integer.parseInt(count);
            double itemPrice = Double.parseDouble(price);
//            Connection conn = (Connection) mysql.getConnection();
            Connection conn = mysql.openConnection();

            boolean success = MenuDao.createMenu((java.sql.Connection) conn, itemId, name, itemCount, itemPrice, empName);
            if (success) {
                JOptionPane.showMessageDialog(null, "Order created successfully!");
                new NextForm().setVisible(true);  // replace with your actual next interface
            }
        } catch (HeadlessException | NumberFormatException  e) {
            JOptionPane.showMessageDialog(null, "Error creating order: " + e.getMessage());
        }
    }

//    public static void handleUpdate(String id, String name, String count, String price, String empName) {
//        if (id.isEmpty() || name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        try {
//            int itemId = Integer.parseInt(id);
//            int itemCount = Integer.parseInt(count);
//            double itemPrice = Double.parseDouble(price);
////            Connection conn = (Connection) DBConnection.getConnection();
//            boolean success = MenuDao.update((java.sql.Connection) conn, itemId, name, itemCount, itemPrice, empName);
//            if (success) {
//                JOptionPane.showMessageDialog(null, "Order updated successfully!");
//                new NextForm().setVisible(true);
//            }
//        } catch (HeadlessException | NumberFormatException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error updating order: " + e.getMessage());
//        }
//    }
//
    public static void handleDelete(String id) {
        MySqlConnection mysql = new MySqlConnection();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Item ID!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemId = Integer.parseInt(id);
//            Connection conn = (Connection) DBConnection.getConnection();
               Connection conn = mysql.openConnection();

            boolean success = MenuDao.deleteMenu((java.sql.Connection) conn, itemId);
            if (success) {
                JOptionPane.showMessageDialog(null, "Order deleted successfully!");
                new NextForm().setVisible(true);
            }
        } catch (HeadlessException | NumberFormatException  e) {
            JOptionPane.showMessageDialog(null, "Error deleting order: " + e.getMessage());
        }
    }

    public static void create(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
    }

    public static void update(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
    }

    public static void deleteOrder(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
    }

    public static void delete(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
    }
}
