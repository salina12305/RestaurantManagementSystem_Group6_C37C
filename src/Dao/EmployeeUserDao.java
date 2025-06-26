//
//package Dao;
//import Database.MySqlConnection;
//import Model.ESigninRequest;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import Model.User;
//import Model.SigninRequest;
///**
// *
// * @author uttu
// */
//public class EmployeeUserDao {
//     MySqlConnection mysql = new MySqlConnection();
//     
//     public void UserDao(User employeeuser) {
//        Connection conn = mysql.openConnection();
//        String sql = "INSERT INTO employee_users (company_name, email, password, confirm_password) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, employeeuser.getCompanyName());
//            pstmt.setString(2, employeeuser.getEmail());
//            pstmt.setString(3, employeeuser.getPassword());
//            pstmt.setString(4, employeeuser.getConfirmPassword());
//
//            pstmt.executeUpdate();
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
////        return false;
//        
//     }
//     
//     // Check if email already exists
//    public boolean isEmailExists(String email) {
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT 1 FROM employee_users WHERE email = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, email);
//            ResultSet result = pstmt.executeQuery();
//            return result.next();  // returns true if email is found
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return false;
//    }
//    
//    public User esignIn(ESigninRequest signin){
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT * FROM employee_users where email = ? and password = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, signin.getEmail());
//            pstmt.setString(2, signin.getPassword());
//            ResultSet result = pstmt.executeQuery();
//            if(result.next()){
//                User employeeuser  = new User(
//                    result.getString("company_name"),
//                    result.getString("email"),
//                    result.getString("password"),
//                    result.getString("confirm_password")
//                );
//                employeeuser.setId(result.getInt("id"));
//                
//                return employeeuser;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return null;
//    }
//    
//    public boolean CheckUser(User employeeuser){
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT * FROM employee_users WHERE email = ? AND password = ?";
//        try(PreparedStatement pstm = conn.prepareStatement(sql)){
//            pstm.setString(1, employeeuser.getEmail());
//            pstm.setString(2, employeeuser.getPassword());
//            ResultSet result = pstm.executeQuery();
//            return result.next();
//        }catch(Exception e){
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return false;
//    }
//}
