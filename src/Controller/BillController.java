
package Controller;

import Dao.BillDao;
import Model.BillModel;
import View.Bill;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.OrderFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BillController { 
    private final BillDao billDao = new BillDao();
    private final Bill billView;

    public BillController(Bill view) {
        this.billView = view;
        this.billView.addGenerateBillListener(new LoadBillListener());
        view.addLogoutListener(new LogoutListener(view));
        billView.addOrderListener(new OrderListener());
        billView.addDashboardListener(new DashboardListener());
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
            tableModel.setRowCount(0); 

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
    public void setupLogoutListener(Bill view) {
        view.addLogoutListener(new LogoutListener(view));
    }

    class LogoutListener implements ActionListener {
        private JFrame currentFrame;

        public LogoutListener(JFrame frame) {
            this.currentFrame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Logout button clicked");
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                currentFrame.dispose(); // Close dashboard
                new EmployeeSignIn().setVisible(true); 
            }
        }
    }
    public void setupOrderListener(Bill view) {
        view.addOrderListener(new OrderListener());
    }
     
    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Order button clicked"); 
            OrderFrame orderView = new OrderFrame();
            new OrderController(orderView);
            orderView.setVisible(true);

            if (billView != null) billView.dispose();
        }
    }
    public void setupDashboardListener(Bill view) {
        view.addDashboardListener(new DashboardListener());
    }
     
    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Dashboard button clicked"); 
            EmployeeDashboard dashboardView = new EmployeeDashboard();
            new EDashboardController(dashboardView);
            dashboardView.setVisible(true);
            
            if (billView != null) billView.dispose();
        }
    }
}
