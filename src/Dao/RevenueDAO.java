/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Nitro
 */
import java.sql.*;
import java.util.*;
import Model.RevenueEntry;

public class RevenueDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "qwerty1234";

    public static List<RevenueEntry> getMonthlyRevenues() {
        List<RevenueEntry> revenueList = new ArrayList<>();

        String sql = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS total " +
                     "FROM orders GROUP BY MONTH(order_date) ORDER BY MONTH(order_date)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                revenueList.add(new RevenueEntry(rs.getInt("month"), rs.getDouble("total")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueList;
    }
}

