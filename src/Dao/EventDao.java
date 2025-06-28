package Dao;

import Database.MySqlConnection;
import Model.EventModel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDao {
    MySqlConnection mysql = new MySqlConnection();

    public void createEvent(EventModel event) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO events (customerName, event, date, staffAssigned, floor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getCustomerName());
            pstmt.setString(2, event.getEvent());
            pstmt.setInt(3, event.getDate());
            pstmt.setString(4, event.getStaffAssigned());
            pstmt.setInt(5, event.getFloor());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean updateEvent(EventModel event, int eventId) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE events SET customerName=?, event=?, date=?, staffAssigned=?, floor=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getCustomerName());
            pstmt.setString(2, event.getEvent());
            pstmt.setInt(3, event.getDate());
            pstmt.setString(4, event.getStaffAssigned());
            pstmt.setInt(5, event.getFloor());
            pstmt.setInt(6, eventId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    public boolean cancelEvent(int eventId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM events WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}
