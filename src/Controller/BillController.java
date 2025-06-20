package controller;

import View.Bill;         
import Dao.BillDao;       

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class BillController {
    
    private final BillDao billDao = new BillDao();
    private final Bill billView;

    public BillController(Bill billView) {
        this.billView = billView;
        billView.addGenerateBillListener(new GenerateBillListener());
    }
    public void open(){
        this.billView.setVisible(true);
    }
    public void close(){
        this.billView.dispose();
    }
    

    class GenerateBillListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String customer_name= billView.getCustomerNameField().getText();
            String phone_number= billView.getPhoneNumberField().getText(); 
            
            
//            System.out.println("Bill generated successfully!");
//            JOptionPane.showMessageDialog(null,"bill generated successfully!");
//            JTable table = billView.getBillTable();
//            System.out.println("Generate Bill clicked");

            boolean rowInserted = false;
            if (rowInserted) {
                JOptionPane.showMessageDialog(null,"Bill generated successfully!");
            } else {
             JOptionPane.showMessageDialog(null,"Bill generation failed!");
}

            
            
            JTable table = billView.getBillTable();

            for (int i = 0; i < table.getRowCount(); i++) {
                try {
                    Object itemObj = table.getValueAt(i, 1); // Items column
                    if (itemObj == null || itemObj.toString().trim().isEmpty()) {
                        continue; // skip empty rows
                    }

                    String item = table.getValueAt(i, 1).toString();
                    int qty = Integer.parseInt(table.getValueAt(i, 2).toString());
                    double rate = Double.parseDouble(table.getValueAt(i, 3).toString());
                    double amount = Double.parseDouble(table.getValueAt(i, 4).toString());
                    double discount = Double.parseDouble(table.getValueAt(i, 5).toString().replace("%", ""));
                    double tax = Double.parseDouble(table.getValueAt(i, 6).toString().replace("%", ""));
                    double total = Double.parseDouble(table.getValueAt(i, 7).toString());

                    // ❗ Use full name to avoid class name clash
                    model.Bill bill = new model.Bill(item, qty, rate, amount, discount, tax, total);
                    billDao.saveBill(bill);
                    rowInserted = true;

                } catch (Exception ex) {
                    System.out.println("Error at row " + i + ": " + ex.getMessage());
                }
            }
  
            if (rowInserted) {
    JOptionPane.showMessageDialog(billView, "Bill generated successfully!");
} else {
    JOptionPane.showMessageDialog(billView, "No valid bill items to save.");
}

        }
    }
}
