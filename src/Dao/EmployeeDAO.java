/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Nitro
*/
import Database.MySqlConnection;
import Model.Employee;

import java.sql.*;

public class EmployeeDAO {
    MySqlConnection mysql = new MySqlConnection();

    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, gender, status, dob, role, shift, department, join_date, address, email, phone, imagePath) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getGender());
            stmt.setString(3, emp.getStatus());
            stmt.setDate(4, emp.getDOB());
            stmt.setString(5, emp.getRole());
            stmt.setString(6, emp.getShift());
            stmt.setString(7, emp.getDepartment());
            stmt.setDate(8, emp.getJoinDate());
            stmt.setString(9, emp.getAddress());
            stmt.setString(10, emp.getEmail());
            stmt.setString(11, emp.getPhone());
            stmt.setString(12, emp.getImagePath());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee getEmployeeByName(String name) {
        String sql = "SELECT * FROM employees WHERE name = ?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name=?, gender=?, status=?, dob=?, role=?, shift=?, department=?, join_date=?, address=?, email=?, phone=?, imagePath=? WHERE emp_id=?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getGender());
            stmt.setString(3, emp.getStatus());
            stmt.setDate(4, emp.getDOB());
            stmt.setString(5, emp.getRole());
            stmt.setString(6, emp.getShift());
            stmt.setString(7, emp.getDepartment());
            stmt.setDate(8, emp.getJoinDate());
            stmt.setString(9, emp.getAddress());
            stmt.setString(10, emp.getEmail());
            stmt.setString(11, emp.getPhone());
            stmt.setString(12, emp.getImagePath());
            stmt.setInt(13, emp.getEmpId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("status"),
                rs.getDate("dob"),
                rs.getString("role"),
                rs.getString("shift"),
                rs.getString("department"),
                rs.getDate("join_date"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("imagePath")
        );
    }
}




