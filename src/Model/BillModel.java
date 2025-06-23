package Model;

public class BillModel {
    private int id;
    private String item;
    private int quantity;
    private double rate;
    private double amount;
    private double discount;
    private double tax;
    private double total;
    private String customerName; 

    public BillModel(int id, String item, int quantity, double rate, double amount, double discount, double tax, double total,String customerName) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.discount = discount;
        this.tax = tax;
        this.total = total;
        this.customerName = customerName;
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getItem() {
        return item;
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

    public double getTotal() {
        return total;
    }
    
    public String getCustomerName() {
        return customerName;
     }
    
    // Setters
    public void setId(int id){
        this.id=id;
    }
    public void setItemName(String item) {
        this.item = item;
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

    public void setTotalPrice(double total) {
        this.total = total;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName= customerName;
    }
}
