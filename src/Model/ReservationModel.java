
package Model;

import java.util.Date;


public class ReservationModel {
    private int floor;
    private String assignedStaff;
    private Date date;   

public ReservationModel(int floor, String assignedStaff, Date date) {
        this.floor = floor;
        this.assignedStaff = assignedStaff;
        this.date = date;
}
    public int getFloor() { 
        return floor; 
    }
    public String getAssignedStaff() {
        return assignedStaff; 
    }
    public Date getDate() { 
        return date; 
    }

    public void setFloor(int floor) {
        this.floor = floor; 
    }
    public void setAssignedStaff(String assignedStaff) { 
        this.assignedStaff = assignedStaff; 
    }
    public void setDate(Date date) {
        this.date = date; 
    }
}
