package Dao;

import Model.MenuItemModel;
//import Database.DBConnection;
import Database.MySqlConnection;
import java.awt.MenuItem;
import java.sql.*;
import java.util.ArrayList;

public class MenuDAO {

    public static boolean createMenu(Connection conn, int itemId, String name, int itemCount, double itemPrice, String empName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean deleteMenu(Connection connection, int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Connection connection;
    
    public MenuDAO() {
        MySqlConnection mysql = new MySqlConnection();
        
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS menu_items (" +
                          "item_id VARCHAR(50) PRIMARY KEY, " +
                          "item_name VARCHAR(100), " +
                          "quantity INT, " +
                          "price DOUBLE, " +
                          "employee_name VARCHAR(100))";
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean createMenuItem(MenuItemModel item) {
        try {
            String query = "INSERT INTO menu_items (item_name, quantity, price, employee_name) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getQuantity());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getEmployeeName());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
//    public boolean updateMenuItem(MenuItemModel item) {
//        try {
//            String query = "UPDATE menu_items SET item_name=?, quantity=?, price=?, employee_name=? WHERE item_id=?";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, item.getItemName());
//            stmt.setInt(2, item.getQuantity());
//            stmt.setDouble(3, item.getPrice());
//            stmt.setString(4, item.getEmployeeName());
//            stmt.setString(5, item.getItemId());
//            
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
    public boolean deleteMenuItem(String itemId) {
        try {
            String query = "DELETE FROM menu_items WHERE item_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, itemId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public MenuItemModel[] getAllMenuItems() {
        try {
            String query = "SELECT * FROM menu_items";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<MenuItemModel> items = new ArrayList<>();
            while (rs.next()) {
                MenuItemModel item = new MenuItemModel(
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("employee_name")
                );
                items.add(item);
            }
            
            return items.toArray(new MenuItemModel[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new MenuItemModel[0];
        }
    }

    public boolean createMenuItem(MenuItem item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
} 