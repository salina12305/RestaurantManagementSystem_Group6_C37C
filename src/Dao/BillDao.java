package Dao;

import Database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.BillModel;  

public class BillDao {
    MySqlConnection mysql = new MySqlConnection();

    public void saveBill(BillModel bill) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO bills (item, quantity, rate, amount, discount, tax, total, customername) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bill.getItem());
            pstmt.setInt(2, bill.getQuantity());
            pstmt.setDouble(3, bill.getRate());
            pstmt.setDouble(4, bill.getAmount());
            pstmt.setDouble(5, bill.getDiscount());
            pstmt.setDouble(6, bill.getTax());
            pstmt.setDouble(7, bill.getTotal());
            pstmt.setString(8, bill.getCustomerName());  // assuming this field exists
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    public List<BillModel> getBillsByCustomerName(String customerName) {
    List<BillModel> billList = new ArrayList<>();
    Connection conn = mysql.openConnection();

    String sql = "SELECT orderId AS id, itemName AS item, noOfItem AS quantity, price AS rate, " +
                 "(price * noOfItem) AS amount, 0 AS discount, (price * noOfItem * 0.13) AS tax, " +
                 "(price * noOfItem * 1.13) AS total, customerName AS customer_name " +
                 "FROM customer_order WHERE customerName = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, customerName);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            BillModel bill = new BillModel(
                rs.getInt("id"),
                rs.getString("item"),
                rs.getInt("quantity"),
                rs.getDouble("rate"),
                rs.getDouble("amount"),
                rs.getDouble("discount"),
                rs.getDouble("tax"),
                rs.getDouble("total"),
                rs.getString("customer_name")
            );
            billList.add(bill);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return billList;
}

}
