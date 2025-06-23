
package Controller;

import Dao.BillDao;
import Model.BillModel;
import View.Bill;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BillController {
    private final Bill billView;
    private final BillDao billDao = new BillDao();

    public BillController(Bill billView) {
        this.billView = billView;
//        this.billDao = new BillDao();

        this.billView.addGenerateBillListener(new LoadBillListener());
    }
     void open() {
       
    }
      void close() {
       
    }

    class LoadBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String customerName = billView.getCustomerNameField().getText().trim();
            if (customerName.isEmpty()) {
                billView.showMessage("Please enter a customer name.");
                return;
            }

            List<BillModel> bills = billDao.getBillsByCustomerName(customerName);
            if (bills.isEmpty()) {
                billView.showMessage("No records found for: " + customerName);
                return;
            }

            DefaultTableModel tableModel = (DefaultTableModel) billView.getBillTable().getModel();
            tableModel.setRowCount(0); // Clear previous data

            for (BillModel bill : bills) {
                tableModel.addRow(new Object[]{
                    bill.getId(),
                    bill.getItem(),
                    bill.getQuantity(),
                    bill.getRate(),
                    bill.getAmount(),
                    bill.getDiscount(),
                    bill.getTax(),
                    bill.getTotal()
                });
            }
        }
    }
    public void setupLogoutListener() {
        billView.addLogoutListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logout clicked");
            }
        });
    }
}
