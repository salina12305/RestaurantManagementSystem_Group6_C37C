/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nitro
 */
public class CustomerOrder {
    private int orderId;
    private String itemName;
    private int noOfItem;
    private double price;
    private String customerName;

    public CustomerOrder(int orderId, String itemName, int noOfItem, double price, String customerName) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.noOfItem = noOfItem;
        this.price = price;
        this.customerName = customerName;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public double getPrice() {
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }


    // Setters if needed (optional)
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

