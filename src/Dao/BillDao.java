package Dao;

import model.Bill;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDao {
    MySqlConnection mysql = new MySqlConnection();

    public void saveBill(Bill bill) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO bills (itemName, quantity, rate, amount, discount, tax, totalPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bill.getItemName());
            pstmt.setInt(2, bill.getQuantity());
            pstmt.setDouble(3, bill.getRate());
            pstmt.setDouble(4, bill.getAmount());
            pstmt.setDouble(5, bill.getDiscount());
            pstmt.setDouble(6, bill.getTax());
            pstmt.setDouble(7, bill.getTotalPrice());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
