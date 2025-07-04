 
package Dao;

import Database.MySqlConnection;
import Model.MenuItemModel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuDAO {
    MySqlConnection mysql = new MySqlConnection();

    //to create menu
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


    //to delete menu
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
