
package Controller;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;

public class PdfBillGenerator {

    public static void generateBillPDF(String customerName, JTable billTable) {
        Document document = new Document();

        try {
            String fileName =  System.getProperty("user.home") + "/Desktop/Bill_" + customerName.replaceAll("\\s+", "") + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Title
            Paragraph title = new Paragraph("Restaurant Bill", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Customer name
            Paragraph customer = new Paragraph("Customer: " + customerName);
            customer.setSpacingBefore(10);
            customer.setSpacingAfter(20);
            document.add(customer);

            // Table with same columns as your JTable
            TableModel model = billTable.getModel();
            PdfPTable pdfTable = new PdfPTable(model.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Add table headers
            for (int i = 0; i < model.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Add table rows
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    pdfTable.addCell(value != null ? value.toString() : "");
                }
            }

            document.add(pdfTable);

            // Thank you note
            Paragraph thanks = new Paragraph("\nWe hope you enjoyed your meal!\nThank you for dining with us at 7~11 Restaurant.\n" +
    "We look forward to serving you again soon.", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 14));
            thanks.setAlignment(Element.ALIGN_CENTER);
            document.add(thanks);

            document.close();

            javax.swing.JOptionPane.showMessageDialog(null, " Bill generated successfully:\n" + fileName);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
        }
    }
}

