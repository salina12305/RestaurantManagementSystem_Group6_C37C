/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Nitro
 */
public class AdminDashboardDao {
    




    public DefaultCategoryDataset getMonthlyRevenueDataset() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String url = "";
        String user = "root";
        String password = "12345678";

        String query = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS revenue " +
                       "FROM orders GROUP BY MONTH(order_date)";


        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("month");
                double revenue = rs.getDouble("revenue");
                String monthName = Month.of(month).name().substring(0, 3); // JAN, FEB, etc.
                dataset.addValue(revenue, "Revenue", monthName);
            }

        }

        return dataset;
    }
}


