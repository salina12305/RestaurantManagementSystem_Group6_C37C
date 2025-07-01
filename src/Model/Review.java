/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nitro
 */
public class Review {
    private String customerName;
    private String reviewText;

    public Review(String customerName, String reviewText) {
        this.customerName = customerName;
        this.reviewText = reviewText;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getReviewText() {
        return reviewText;
    }
}

