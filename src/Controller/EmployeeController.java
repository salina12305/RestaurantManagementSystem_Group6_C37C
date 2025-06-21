/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Nitro
 */
import Dao.EmployeeDAO;
import Model.Employee;

import javax.swing.*;
import java.sql.Date;

public class EmployeeController {
    private JTextField txtID, txtName, txtGender, txtStatus, txtRating, txtRole,
            txtShift, txtDepartment, txtJoinedDate, txtAddress,
            txtEmail, txtPhoneNumber, txtDob;

    public EmployeeController(JTextField txtID, JTextField txtName, JTextField txtGender, JTextField txtStatus,
                              JTextField txtRating, JTextField txtRole, JTextField txtShift, JTextField txtDepartment,
                              JTextField txtJoinedDate, JTextField txtAddress, JTextField txtEmail,
                              JTextField txtPhoneNumber, JTextField txtDob) {
        this.txtID = txtID;
        this.txtName = txtName;
        this.txtGender = txtGender;
        this.txtStatus = txtStatus;
        this.txtRating = txtRating;
        this.txtRole = txtRole;
        this.txtShift = txtShift;
        this.txtDepartment = txtDepartment;
        this.txtJoinedDate = txtJoinedDate;
        this.txtAddress = txtAddress;
        this.txtEmail = txtEmail;
        this.txtPhoneNumber = txtPhoneNumber;
        this.txtDob = txtDob;
    }

    public void loadEmployee() {
        try {
            int id = Integer.parseInt(txtID.getText());
            Employee emp = new EmployeeDAO().getEmployeeById(id);

            if (emp != null) {
                txtName.setText(emp.getName());
                txtGender.setText(emp.getGender());
                txtStatus.setText(emp.getStatus());
                txtRating.setText(String.valueOf(emp.getRating()));
                txtRole.setText(emp.getRole());
                txtShift.setText(emp.getShift());
                txtDepartment.setText(emp.getDepartment());
                txtJoinedDate.setText(emp.getJoinDate() != null ? emp.getJoinDate().toString() : "");
                txtAddress.setText(emp.getAddress());
                txtEmail.setText(emp.getEmail());
                txtPhoneNumber.setText(emp.getPhone());
                txtDob.setText(emp.getDob() != null ? emp.getDob().toString() : "");
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
        }
    }

    public void saveEmployee() {
        try {
            Employee emp = getFormData(false);
            boolean success = new EmployeeDAO().addEmployee(emp);
            JOptionPane.showMessageDialog(null, success ? "Employee added!" : "Failed to add.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving employee: " + e.getMessage());
        }
    }

    public void updateEmployee() {
        try {
            Employee emp = getFormData(true);
            boolean success = new EmployeeDAO().updateEmployee(emp);
            JOptionPane.showMessageDialog(null, success ? "Employee updated!" : "Update failed.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage());
        }
    }

    public void deleteEmployee() {
        try {
            int id = Integer.parseInt(txtID.getText());
            boolean success = new EmployeeDAO().deleteEmployee(id);
            JOptionPane.showMessageDialog(null, success ? "Employee deleted!" : "Delete failed.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
        }
    }

    private Employee getFormData(boolean includeId) {
        int id = includeId ? Integer.parseInt(txtID.getText()) : 0;
        double rating = Double.parseDouble(txtRating.getText());

        Date joinDate = Date.valueOf(txtJoinedDate.getText().trim());
        Date dob = Date.valueOf(txtDob.getText().trim());

        return new Employee(
                id,
                txtName.getText(),
                txtGender.getText(),
                txtStatus.getText(),
                rating,
                txtRole.getText(),
                txtShift.getText(),
                txtDepartment.getText(),
                joinDate,
                txtAddress.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText(),
                dob
        );
    }
    
    public void loadEmployeeByName() {
    String name = txtName.getText().trim();
    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a name to search.");
        return;
    }

    Employee emp = new EmployeeDAO().getEmployeeByName(name);

    if (emp != null) {
        txtID.setText(String.valueOf(emp.getEmpId()));
        txtGender.setText(emp.getGender());
        txtStatus.setText(emp.getStatus());
        txtRating.setText(String.valueOf(emp.getRating()));
        txtRole.setText(emp.getRole());
        txtShift.setText(emp.getShift());
        txtDepartment.setText(emp.getDepartment());
        txtJoinedDate.setText(emp.getJoinDate() != null ? emp.getJoinDate().toString() : "");
        txtAddress.setText(emp.getAddress());
        txtEmail.setText(emp.getEmail());
        txtPhoneNumber.setText(emp.getPhone());
        txtDob.setText(emp.getDob() != null ? emp.getDob().toString() : "");
    } else {
        JOptionPane.showMessageDialog(null, "Employee not found.");
    }
}

}
