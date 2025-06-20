package Model;

public class Order {
    private int orderId;
    private String itemName;
    private int noOfItem;
    private double price;
    private String customerName;
    private String employeeName;

    public Order(int orderId, String itemName, int noOfItem, double price, String customerName, String employeeName) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.noOfItem = noOfItem;
        this.price = price;
        this.customerName = customerName;
        this.employeeName = employeeName;
    }

    public Order(String itemName, int noOfItem, double price, String customerName, String employeeName) {
        this.itemName = itemName;
        this.noOfItem = noOfItem;
        this.price = price;
        this.customerName = customerName;
        this.employeeName = employeeName;
    }

    public int getOrderId() { return orderId; }
    public String getItemName() { return itemName; }
    public int getNoOfItem() { return noOfItem; }
    public double getPrice() { return price; }
    public String getCustomerName() { return customerName; }
    public String getEmployeeName() { return employeeName; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setNoOfItem(int noOfItem) { this.noOfItem = noOfItem; }
    public void setPrice(double price) { this.price = price; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
}
