/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.User;

/**
 *
 * @author uttu
 */
public class UserDao {
    MySqlConnection mysql = new MySqlConnection();
    
   public void SignIn (User User){
    Connection conn= mysql.openConnection();
    
    
    String sql = "Insert into users(email,password) VALUES (?,?)";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1,User.getEmail());
            pstmt.setString(1,User.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException ex){
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
          mysql.closeConnection(conn);
        }
    }
      public boolean checkUser(User User){
          Connection conn = mysql.openConnection();
          String sql = "SELECT * FROM users where email = ?";
          try (PreparedStatement pstmt=conn.prepareStatement(sql)){
             pstmt.setString(1,User.getEmail());
             ResultSet result=pstmt.executeQuery();
             return result.next();   
            }catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE,null,ex);
            }finally {
              mysql.closeConnection(conn);
            }
            return false;

    }
}

