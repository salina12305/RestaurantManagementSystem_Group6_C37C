/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Nitro
 */

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;

public class PdfBillGenerator {

    public static void generateBillPDF(String customerName, JTable billTable) {
        Document document = new Document();

        try {
            String fileName = "Bill_" + customerName.replaceAll("\\s+", "") + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            
            Paragraph title = new Paragraph("Restaurant Bill", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

       
            Paragraph customer = new Paragraph("Customer: " + customerName);
            customer.setSpacingBefore(10);
            customer.setSpacingAfter(20);
            document.add(customer);

            
            TableModel model = billTable.getModel();
            PdfPTable pdfTable = new PdfPTable(model.getColumnCount());
            pdfTable.setWidthPercentage(100);

            
            for (int i = 0; i < model.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    pdfTable.addCell(value != null ? value.toString() : "");
                }
            }

            document.add(pdfTable);

            
            Paragraph thanks = new Paragraph("\nThank you for your business!", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 14));
            thanks.setAlignment(Element.ALIGN_CENTER);
            document.add(thanks);

            document.close();

            javax.swing.JOptionPane.showMessageDialog(null, "PDF Bill generated successfully:\n" + fileName);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
        }
    }
}

