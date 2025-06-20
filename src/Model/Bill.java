package model;

public class Bill {
    private String itemName;
    private int quantity;
    private double rate;
    private double amount;
    private double discount;
    private double tax;
    private double totalPrice;

    public Bill(String itemName, int quantity, double rate, double amount, double discount, double tax, double totalPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.discount = discount;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
