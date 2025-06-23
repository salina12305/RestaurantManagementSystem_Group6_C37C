

package Dao;

import Database.MySqlConnection;
import Model.ESigninRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.User;
import Model.SigninRequest;


public class UserDao {

    MySqlConnection mysql = new MySqlConnection();

 
      public void UserDao(User user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (company_name, email, password, confirm_password,role) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getCompanyName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getConfirmPassword());
            pstmt.setString(5, user.getRole());

            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
//        return false;
        
    }
    
    // Check if admin login
    public User signIn(SigninRequest login){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login.getEmail());
            pstmt.setString(2, login.getPassword());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                User user  = new User(
                    result.getString("company_name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("confirm_password"),
                    result.getString("role")
                );
                user.setId(result.getInt("id"));
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
//    
    }
    public boolean CheckUser(User user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getPassword());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(Exception e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
//    public User signIn(ESigninRequest signin){
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT * FROM users where email = ? and password = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, signin.getEmail());
//            pstmt.setString(2, signin.getPassword());
//            ResultSet result = pstmt.executeQuery();
//            if(result.next()){
//                User user  = new User(
//                    result.getString("company_name"),
//                    result.getString("email"),
//                    result.getString("password"),
//                    result.getString("confirm_password"),
//                    result.getString("role")
//                );
//                user.setId(result.getInt("id"));
//                
//                return user;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return null;
//    }
//

    
    public User esignIn(ESigninRequest signin){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, signin.getEmail());
            pstmt.setString(2, signin.getPassword());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                User user  = new User(
                    result.getString("company_name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("confirm_password"),
                    result.getString("role")
                );
                user.setId(result.getInt("id"));
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
}
