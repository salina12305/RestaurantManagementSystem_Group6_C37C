/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Nitro
 */
import Database.MySqlConnection;
import Model.CustomerOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao {
    private final MySqlConnection mysql = new MySqlConnection();

    public List<CustomerOrder> getOrdersByCustomerName(String customerName) {
        List<CustomerOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM customer_order WHERE customerName = ?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String itemName = rs.getString("itemName");
                int noOfItem = rs.getInt("noOfItem");
                double price = rs.getDouble("price");
                

                CustomerOrder order = new CustomerOrder(orderId, itemName, noOfItem, price, customerName);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}

