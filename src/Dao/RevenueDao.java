
package Dao;

import Database.MySqlConnection;
import Model.SigninRequest;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;


public class RevenueDao {

    MySqlConnection mysql = new MySqlConnection();


      public void saveOrder(User user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO orders (order_date, total_amount) VALUES (?, ?)";
        try {
    String query = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS revenue FROM orders GROUP BY MONTH(order_date)";
    PreparedStatement stmt = conn.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();

    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // define it if not passed

    while (rs.next()) {
        int month = rs.getInt("month");
        double revenue = rs.getDouble("revenue");

        if (month >= 1 && month <= 12) {
            String monthName = Month.of(month).name().substring(0, 3); // JAN, FEB, etc.
            dataset.addValue(revenue, "Revenue", monthName);
        }
    }
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error loading revenue chart: " + e.getMessage());     
}   
    }

        

    // Check  order 
    public boolean order(String order) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT 1 FROM users WHERE order= ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order);
            ResultSet result = pstmt.executeQuery();
            return result.next();  
        } catch (SQLException ex) {
           Logger.getLogger(RevenueDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
   public void reviewOrder(User user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO reviews (order_date, total_amount) VALUES (?, ?)";
        try {
    String query = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS revenue FROM orders GROUP BY MONTH(order_date)";
    PreparedStatement stmt = conn.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();

    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // define it if not passed

    while (rs.next()) {
        int month = rs.getInt("month");
        double revenue = rs.getDouble("revenue");

        if (month >= 1 && month <= 12) {
            String monthName = Month.of(month).name().substring(0, 3); // JAN, FEB, etc.
            dataset.addValue(revenue, "Revenue", monthName);
        }
    }
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error loading revenue chart: " + e.getMessage());     
}   
    }
}
