///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Dao;
//
//import Database.MySqlConnection;
//import Model.SigninRequest;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
///**
// *
// * @author uttu
// */
//public class LoginDao {
//    MySqlConnection mysql = new MySqlConnection();
//    public boolean validateUser(SigninRequest userLogin){
//        
//    
//   Connection conn = mysql.openConnection();
//        String sql = "SELECT * FROM users where email = ? and password = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, userLogin.getEmail());
//            pstmt.setString(2, userLogin.getPassword());
//            ResultSet result = pstmt.executeQuery();
//            
//            return result.next();
//}catch (Exception ex){
//    
//}finally{
//            mysql.closeConnection(conn);
//        }
//return false;
//}
//}
//
