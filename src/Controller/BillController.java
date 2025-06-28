package controller;

import View.Bill;
import Dao.CustomerOrderDao;
import Model.CustomerOrder;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;

public class BillController {
    private final Bill billView;

    public BillController(Bill billView) {
        this.billView = billView;
        this.billView.addGenerateBillListener(new GenerateBillListener());
    }

    public void open() {
        billView.setVisible(true);
    }

    public void close() {
        billView.dispose();
    }

    class GenerateBillListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String customerName = billView.getCustomerNameField().getText().trim();

        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(billView, "Customer name is required.");
            return;
        }

        JTable table = billView.getBillTable();
        Dao.CustomerOrderDao orderDao = new Dao.CustomerOrderDao();
        Dao.BillDao billDao = new Dao.BillDao();
        boolean orderInserted = false;
        boolean billInserted = false;

        // Save each order row into customer_order table
        for (int i = 0; i < table.getRowCount(); i++) {
            Object itemObj = table.getValueAt(i, 1); // Item column
            if (itemObj == null || itemObj.toString().trim().isEmpty()) continue;

            try {
                String itemName = table.getValueAt(i, 1).toString();
                int qty = Integer.parseInt(table.getValueAt(i, 2).toString());
                double rate = Double.parseDouble(table.getValueAt(i, 3).toString());
                double amount = Double.parseDouble(table.getValueAt(i, 4).toString());
                double discount = Double.parseDouble(table.getValueAt(i, 5).toString().replace("%", ""));
                double tax = Double.parseDouble(table.getValueAt(i, 6).toString().replace("%", ""));
                double total = Double.parseDouble(table.getValueAt(i, 7).toString());

                // Save order info
                Model.CustomerOrder order = new model.CustomerOrder(itemName, qty, rate, customerName);
                if (orderDao.saveOrder(order)) {
                    orderInserted = true;
                }

                // Save bill info
                model.Bill bill = new model.Bill(itemName, qty, rate, amount, discount, tax, total);
                billDao.saveBill(bill);
                billInserted = true;

            } catch (Exception ex) {
                System.out.println("Error processing row " + i + ": " + ex.getMessage());
            }
        }

        if (!orderInserted) {
            JOptionPane.showMessageDialog(billView, "Failed to save order details. Bill generation aborted.");
            return;
        }

        if (!billInserted) {
            JOptionPane.showMessageDialog(billView, "Failed to save bill details.");
        } else {
            JOptionPane.showMessageDialog(billView, "Order saved and Bill generated successfully!");
        }

        // Now generate PDF bill
        generatePDFBill(customerName, table);
    }

    private void generatePDFBill(String customerName, JTable table) {
        // PDF generation using iText or your preferred library
        // Example with iText:

        try {
            String fileName = "Bill_" + customerName + "_" + System.currentTimeMillis() + ".pdf";
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(fileName));
            document.open();

            document.add(new com.itextpdf.text.Paragraph("Bill for Customer: " + customerName));
            document.add(new com.itextpdf.text.Paragraph("----------------------------------------------------"));

            // Add each row details
            for (int i = 0; i < table.getRowCount(); i++) {
                Object itemObj = table.getValueAt(i, 1);
                if (itemObj == null || itemObj.toString().trim().isEmpty()) continue;

                String itemName = table.getValueAt(i, 1).toString();
                String qty = table.getValueAt(i, 2).toString();
                String rate = table.getValueAt(i, 3).toString();
                String amount = table.getValueAt(i, 4).toString();
                String discount = table.getValueAt(i, 5).toString();
                String tax = table.getValueAt(i, 6).toString();
                String total = table.getValueAt(i, 7).toString();

                String line = String.format("%s | Qty: %s | Rate: %s | Amount: %s | Disc: %s | Tax: %s | Total: %s",
                    itemName, qty, rate, amount, discount, tax, total);

                document.add(new com.itextpdf.text.Paragraph(line));
            }

            document.close();

            JOptionPane.showMessageDialog(billView, "PDF Bill saved as: " + fileName);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(billView, "Error generating PDF: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

}
