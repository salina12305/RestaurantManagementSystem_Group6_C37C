
package Controller;


import Dao.EmployeeDAO;
import Model.Employee;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Date;

public class EmployeeController {
    private JTextField txtID, txtName, txtGender, txtStatus, txtDOB, txtRole,
            txtShift, txtDepartment, txtJoinedDate, txtAddress,
            txtEmail, txtPhoneNumber;
    private JLabel lblProfileImage;
    private String imagePath;

    public EmployeeController(
        JTextField txtID, JTextField txtName, JTextField txtGender, JTextField txtStatus,
        JTextField txtDOB, JTextField txtRole, JTextField txtShift, JTextField txtDepartment,
        JTextField txtJoinedDate,  JTextField txtAddress, JTextField txtEmail,
        JTextField txtPhoneNumber, JLabel lblProfileImage
    ) {
        this.txtID = txtID;
        this.txtName = txtName;
        this.txtGender = txtGender;
        this.txtStatus = txtStatus;
          this.txtDOB = txtDOB;
        this.txtRole = txtRole;
        this.txtShift = txtShift;
        this.txtDepartment = txtDepartment;
        this.txtJoinedDate = txtJoinedDate;
        this.txtAddress = txtAddress;
        this.txtEmail = txtEmail;
        this.txtPhoneNumber = txtPhoneNumber;
        this.lblProfileImage = lblProfileImage;
    }

    public void loadEmployee() {
        String keyword = txtID.getText().trim();
        Employee emp;

        try {
            if (keyword.matches("\\d+")) {
                emp = new EmployeeDAO().getEmployeeById(Integer.parseInt(keyword));
            } else {
                emp = new EmployeeDAO().getEmployeeByName(keyword);
            }

            if (emp != null) {
                fillFormWithEmployee(emp);
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading employee: " + e.getMessage());
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

    public void chooseProfileImage() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imagePath = file.getAbsolutePath();
            setProfileImage(imagePath);
        }
    }

    private void fillFormWithEmployee(Employee emp) {
        txtID.setText(String.valueOf(emp.getEmpId()));
        txtName.setText(emp.getName());
        txtGender.setText(emp.getGender());
        txtStatus.setText(emp.getStatus());
        txtDOB.setText(emp.getDOB().toString());
        txtRole.setText(emp.getRole());
        txtShift.setText(emp.getShift());
        txtDepartment.setText(emp.getDepartment());
        txtJoinedDate.setText(emp.getJoinDate().toString());
        txtAddress.setText(emp.getAddress());
        txtEmail.setText(emp.getEmail());
        txtPhoneNumber.setText(emp.getPhone());
        imagePath = emp.getImagePath();

        setProfileImage(imagePath);
    }

    private Employee getFormData(boolean includeId) {
        int id = includeId ? Integer.parseInt(txtID.getText()) : 0;
        Date joinDate = Date.valueOf(txtJoinedDate.getText());
        Date dob = Date.valueOf(txtDOB.getText());

        return new Employee(
                id,
                txtName.getText(),
                txtGender.getText(),
                txtStatus.getText(),
                dob,
                txtRole.getText(),
                txtShift.getText(),
                txtDepartment.getText(),
                joinDate,      
                txtAddress.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText(),
                imagePath
        );
    }

    private void setProfileImage(String path) {
        if (lblProfileImage != null && path != null && !path.isEmpty()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(lblProfileImage.getWidth(), lblProfileImage.getHeight(), Image.SCALE_SMOOTH);
            lblProfileImage.setIcon(new ImageIcon(img));
        }
    }
}


