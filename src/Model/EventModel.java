package Model;

public class EventModel {
    private String customerName;
    private String event;
    private int date;
    private String staffAssigned;
    private int floor;

    public EventModel(String customerName, String event, int date, String staffAssigned, int floor) {
        this.customerName = customerName;
        this.event = event;
        this.date = date;
        this.staffAssigned = staffAssigned;
        this.floor = floor;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEvent() {
        return event;
    }

    public int getDate() {
        return date;
    }

    public String getStaffAssigned() {
        return staffAssigned;
    }

    public int getFloor() {
        return floor;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setStaffAssigned(String staffAssigned) {
        this.staffAssigned = staffAssigned;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
