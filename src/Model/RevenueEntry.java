/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nitro
 */
public class RevenueEntry {
    private int month;
    private double total;

    public RevenueEntry(int month, double total) {
        this.month = month;
        this.total = total;
    }

    public int getMonth() {
        return month;
    }

    public double getTotal() {
        return total;
    }
}

