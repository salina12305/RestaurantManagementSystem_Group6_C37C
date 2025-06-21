/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nitro
 */
import java.sql.Date;

public class Employee {
    private int empId;
    private String name, gender, status, role, shift, department;
    private double rating;
    private Date joinDate;
    private String address, email, phone;
    private Date dob;

    public Employee(int empId, String name, String gender, String status, double rating, String role,
                    String shift, String department, Date joinDate, String address, String email, String phone, Date dob) {
        this.empId = empId;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.rating = rating;
        this.role = role;
        this.shift = shift;
        this.department = department;
        this.joinDate = joinDate;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Employee(String name, String gender, String status, double rating, String role,
                    String shift, String department, Date joinDate, String address, String email, String phone, Date dob) {
        this(0, name, gender, status, rating, role, shift, department, joinDate, address, email, phone, dob);
    }

    // Getters
    public int getEmpId() { return empId; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getStatus() { return status; }
    public double getRating() { return rating; }
    public String getRole() { return role; }
    public String getShift() { return shift; }
    public String getDepartment() { return department; }
    public Date getJoinDate() { return joinDate; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getDob() { return dob; }

    // Setters
    public void setEmpId(int empId) { this.empId = empId; }
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setStatus(String status) { this.status = status; }
    public void setRating(double rating) { this.rating = rating; }
    public void setRole(String role) { this.role = role; }
    public void setShift(String shift) { this.shift = shift; }
    public void setDepartment(String department) { this.department = department; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDob(Date dob) { this.dob = dob; }
}


