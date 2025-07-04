
package Controller;

import Dao.BillDao;
import Model.BillModel;
import View.Bill;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.EventBooking;
import View.ManageMenu;
import View.OrderFrame;
import View.Reservation;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BillController { 
    private final BillDao billDao = new BillDao();
    private final Bill billView;

    public BillController(Bill view) {
        this.billView = view;
        this.billView.addGenerateBillListener(new LoadBillListener());
        
        billView.addOrderListener(new OrderListener());
        billView.addDashboardListener(new DashboardListener());
        billView.addReservationListener(new ReservationListener());
        billView.addEventListener(new EventListener());
        billView.addMenuListener(new MenuListener());
        view.addLogoutListener(new LogoutListener(view));
    }
     public void open() {
        this.billView.setVisible(true);
    }

    public void close() {
        this.billView.dispose();
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
            Controller.PdfBillGenerator.generateBillPDF(customerName, billView.getBillTable());
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
    
    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            OrderFrame orderView = new OrderFrame();
            orderView.setVisible(true);
            OrderController controller = new OrderController(orderView);
            System.out.println(" OrderController  Clicked");
            controller.open();
            close();
        }
    }
    
    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            EmployeeDashboard dashboardView = new EmployeeDashboard();
            dashboardView.setVisible(true);
            EDashboardController controller = new EDashboardController(dashboardView);
            System.out.println(" EDashboardControllers  Clicked");
            controller.open();
            close();
        }
    }

    class ReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                Reservation reservationView = new Reservation();
                reservationView.setVisible(true);
                ReservationController controller = new ReservationController(reservationView);
                System.out.println("reservationView  Clicked");
                controller.open();
                close();
            } catch (SQLException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            EventBooking eventView = new EventBooking();
            eventView.setVisible(true);
            EventController controller = new EventController(eventView);
            System.out.println("EventController  Clicked");
            controller.open();
            close();
        }
    }
 
    class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            ManageMenu menuView = new ManageMenu();
            menuView.setVisible(true);
            MenuController controller = new MenuController(menuView);
            System.out.println("menu  Clicked");
            controller.open();
            close();
        }
    }
}
