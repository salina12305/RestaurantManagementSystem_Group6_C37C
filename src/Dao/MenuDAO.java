//package Dao;
//
//import Model.MenuItemModel;
////import Database.DBConnection;
//import Database.MySqlConnection;
//import java.awt.MenuItem;
//import java.sql.*;
//import java.util.ArrayList;
//
//public class MenuDAO {
//
//    public static boolean createMenu(Connection conn, int itemId, String name, int itemCount, double itemPrice, String empName) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public static boolean deleteMenu(Connection connection, int itemId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    private Connection connection;
//    
//    public MenuDAO() {
//        MySqlConnection mysql = new MySqlConnection();
//        
//        createTableIfNotExists();
//    }
//    
//    private void createTableIfNotExists() {
//        try {
//            String query = "CREATE TABLE IF NOT EXISTS menu_items (" +
//                          "item_id VARCHAR(50) PRIMARY KEY, " +
//                          "item_name VARCHAR(100), " +
//                          "no_of_item int, " +
//                          "price DOUBLE, " +
//                          "employee_name VARCHAR(100))";
//            Statement stmt = connection.createStatement();
//            stmt.execute(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public boolean createMenuItem(MenuItemModel item) {
//        try {
//            String query = "INSERT INTO menu_items (item_name, no_of_item, price, employee_name) VALUES (?, ?, ?, ?)";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, item.getItemName());
//            stmt.setInt(2, item.getQuantity());
//            stmt.setDouble(3, item.getPrice());
//            stmt.setString(4, item.getEmployeeName());
//            
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
////    public boolean updateMenuItem(MenuItemModel item) {
////        try {
////            String query = "UPDATE menu_items SET item_name=?, quantity=?, price=?, employee_name=? WHERE item_id=?";
////            PreparedStatement stmt = connection.prepareStatement(query);
////            stmt.setString(1, item.getItemName());
////            stmt.setInt(2, item.getQuantity());
////            stmt.setDouble(3, item.getPrice());
////            stmt.setString(4, item.getEmployeeName());
////            stmt.setString(5, item.getItemId());
////            
////            return stmt.executeUpdate() > 0;
////        } catch (SQLException e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////    
//    public boolean deleteMenuItem(String itemId) {
//        try {
//            String query = "DELETE FROM menu_items WHERE item_id=?";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, itemId);
//            
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//    public MenuItemModel[] getAllMenuItems() {
//        try {
//            String query = "SELECT * FROM menu_items";
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            
//            ArrayList<MenuItemModel> items = new ArrayList<>();
//            while (rs.next()) {
//                MenuItemModel item = new MenuItemModel(
//                    rs.getString("item_name"),
//                    rs.getInt("no_of_item"),
//                    rs.getDouble("price"),
//                    rs.getString("employee_name")
//                );
//                items.add(item);
//            }
//            
//            return items.toArray(new MenuItemModel[0]);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new MenuItemModel[0];
//        }
//    }
//
//    public boolean createMenuItem(MenuItem item) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//} 


package Dao;

import Database.MySqlConnection;
import Model.MenuItemModel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuDAO {
    MySqlConnection mysql = new MySqlConnection();

    public void createMenuItem(MenuItemModel menu) {
        Connection conn = mysql.openConnection();
        System.out.println("createMenu() called");

        String sql = "INSERT INTO menu_items (item_name, noOfItem, price,employee_name) VALUES (?,?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, menu.getItemName());
            pstmt.setInt(2, menu.getnoOfItem());
            pstmt.setDouble(3, menu.getPrice());           
            pstmt.setString(4, menu.getEmployeeName());

            int result = pstmt.executeUpdate();
            System.out.println("Rows affected: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }

//public boolean updateOrder(MenuItemModel order) {
//    Connection conn = mysql.openConnection();
//    String sql = "UPDATE customer_order SET itemName=?, noOfItem=?, price=?, customerName=?, employeeName=? WHERE orderId=?";
//    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//        pstmt.setString(1, order.getItemName());
//        pstmt.setInt(2, order.getNoOfItem());
//        pstmt.setDouble(3, order.getPrice());
//        
//        pstmt.setString(5, order.getEmployeeName());
//        
//
//        int result = pstmt.executeUpdate();
//        System.out.println("Rows updated: " + result);
//        return result > 0;
//    } catch (SQLException ex) {
//        Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
//    } finally {
//        mysql.closeConnection(conn);
//    }
//    return false;
//}

public boolean deleteMenu(String ItemName) {
    Connection conn = mysql.openConnection();
    String sql = "DELETE FROM menu_items WHERE item_name=?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, ItemName);
        int result = pstmt.executeUpdate();
        System.out.println("Rows deleted: " + result);
        return result > 0;
    } catch (SQLException ex) {
        Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        mysql.closeConnection(conn);
    }
    return false;
}
}
