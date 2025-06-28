package Dao;

import Database.MySqlConnection;
import Model.EventModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDao {
    MySqlConnection mysql = new MySqlConnection();

    public void createEvent(EventModel event) {
        Connection conn = mysql.openConnection();
        System.out.println("createEvent() called");

        String sql = "INSERT INTO events (customerName, event, date, staffAssigned, floor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getCustomerName());
            pstmt.setString(2, event.getEvent());
            pstmt.setDate(3, event.getDate());
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
            pstmt.setDate(3, event.getDate());
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

    public boolean deleteEvent(int eventId) {
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
    /**
     * Fetches distinct floors that currently have events (booked floors).
     * @return Set of booked floor numbers
     */
//    public Set<Integer> getBookedFloors() {
//        Set<Integer> bookedFloors = new HashSet<>();
//        Connection conn = mysql.openConnection();
//
//        String sql = "SELECT DISTINCT floor FROM events";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                bookedFloors.add(rs.getInt("floor"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EventDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return bookedFloors;
//    }
    public Set<Integer> getBookedFloors() throws SQLException {
        Connection conn = mysql.openConnection();
        Set<Integer> bookedFloors = new HashSet<>();
        String sql = "SELECT DISTINCT floor FROM events";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookedFloors.add(rs.getInt("floor"));
            }
        }
        return bookedFloors;
    }
    public List<String[]> getStaffDuty() throws SQLException {
         Connection conn = mysql.openConnection();
        List<String[]> data = new ArrayList<>();
        String sql = "SELECT floor, assigned_staffs, event_date FROM event_bookings";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                data.add(new String[]{
                    rs.getString("floor"),
                    rs.getString("assigned_staffs"),
                    rs.getString("event_date")
                });
            }
        }
        return data;
    }
}
