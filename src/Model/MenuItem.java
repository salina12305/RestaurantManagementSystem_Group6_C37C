package Model;

public class MenuItem {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;
    private String employeeName;
    
    public MenuItem(String itemName, int quantity, double price, String employeeName) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.employeeName = employeeName;
    }
    
    // Getters
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getEmployeeName() { return employeeName; }
    
    // Setters
    public void setItemId(String itemId) { this.itemId = itemId; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
} 