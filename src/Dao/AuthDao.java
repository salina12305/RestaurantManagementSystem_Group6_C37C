
package Dao;

import Database.MySqlConnection;
import Model.SecAnswers;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class AuthDao {
    MySqlConnection  mysql = new MySqlConnection();
    
public void insertSecurityAnswers(SecAnswers user){
    Connection conn = mysql.openConnection();
    String query= "INSERT INTO user_security(Email, answer1, answer2, answer3) VALUES(?,?,?,?)";
    try(PreparedStatement stmt = conn.prepareStatement(query)) {
              stmt.setString(1, user.getEmail());
              stmt.setString(2, user.getanswer1());
              stmt.setString(3, user.getanswer2());
              stmt.setString(4, user.getanswer3());
              
              stmt.executeUpdate();
              System.out.println("Security answers inserted successfully for: "+ user.getEmail());
              
    }catch (SQLException e) {
        e.printStackTrace();
    }finally {
        mysql.closeConnection(conn);
    }
    
}
    public boolean validateSecurityAnswers (String email, String[] answers){
        if(email==null || answers==null)return false;
        Connection conn = mysql.openConnection();
        String query ="SELECT answer1, answer2, answer3 FROM user_security WHERE Email=?";
      try(PreparedStatement stmt = conn.prepareStatement(query)) {
              stmt.setString(1,email);
              ResultSet rs = stmt.executeQuery();
              if(rs.next()) {
                  boolean match1= rs.getString("answer1").equalsIgnoreCase(answers[0]);
                    boolean match2=rs.getString("answer2").equalsIgnoreCase(answers[1]);
                       boolean match3=  rs.getString("answer3").equalsIgnoreCase(answers[2]);
                       return match1 && match2 && match3;
                          
              }
    }catch (SQLException e) {
        Logger.getLogger(AuthDao.class.getName()).log(Level.SEVERE,null,e);
    } finally {
          mysql.closeConnection(conn);
       }
    return false;
}
    public boolean updatePassword(String email, String newPassword){
        if(email==null || newPassword== null)return false;
        Connection conn = mysql.openConnection();
        String query="Update users SET password=?, confirm_password=? WHERE Email=?";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
              stmt.setString(1,newPassword);
              stmt.setString(2,newPassword);
              stmt.setString(3, email);
              return stmt.executeUpdate()>0;
              
    }catch (SQLException e) {
        Logger.getLogger(AuthDao.class.getName()).log(Level.SEVERE,null,e);
    } finally {
            mysql.closeConnection(conn);
        } 
        return false;
    }
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT Email FROM users WHERE Email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
//            return result.next();  // returns true if email is found
        } catch (SQLException e) {
            e.printStackTrace();
             return false;
    }
}
}

    
     


