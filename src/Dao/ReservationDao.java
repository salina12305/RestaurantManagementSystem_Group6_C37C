/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.MySqlConnection;
import Model.ReservationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author uttu
 */
public class ReservationDao {
    MySqlConnection mysql = new MySqlConnection();

//    public List<ReservationModel> getReservationsByFloor(int floor) {
//        Connection conn = mysql.openConnection();
//        List<ReservationModel> reservations = new ArrayList<>();
//        String sql = "SELECT floor, assigned_staff, date FROM reservations WHERE floor = ?";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, floor);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                ReservationModel res = new ReservationModel(
//                    rs.getInt("floor"),
//                    rs.getString("assigned_staff"),
//                    rs.getDate("date")
//                );
//                reservations.add(res);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return reservations;
//    }
}
