package Model;

public class MenuItemModel {
    
    private String itemName;
    private int noOfItem;
    private double price;
    private String employeeName;
    
    public MenuItemModel(String itemName, int noOfItem, double price, String employeeName) {
        this.itemName = itemName;
        this.noOfItem = noOfItem;
        this.price = price;
        this.employeeName = employeeName;
    }
    
   
    //Getters
    public String getItemName() {
        return itemName; 
    }
    public int getnoOfItem() { 
        return noOfItem; 
    }
    public double getPrice() { 
        return price; 
    }
    public String getEmployeeName() {
        return employeeName; 
    }
    
    //Setters
    
    public void setItemName(String itemName) {
        this.itemName = itemName; 
    }
    public void setnoOfItem(int noOfItem) {
        this.noOfItem = noOfItem; 
    }
    public void setPrice(double price) {
        this.price = price; 
    }
    public void setEmployeeName(String employeeName) { 
        this.employeeName = employeeName; 
    }
} 