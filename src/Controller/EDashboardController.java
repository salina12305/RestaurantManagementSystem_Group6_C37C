
package Controller;

import Dao.EDashboardDao;
import Database.MySqlConnection;
import View.Bill;
import View.OrderFrame;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import View.EmployeeDashboard;
import View.EmployeeSignIn; 
import View.EventBooking;
import View.ManageMenu;
import View.Reservation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EDashboardController {

    MySqlConnection mysql = new MySqlConnection();

    private final EDashboardDao edashboardDao = new EDashboardDao(); 
    private final EmployeeDashboard dashboardView;

    public EDashboardController(EmployeeDashboard view) {
        this.dashboardView=view;

        EDashboardDao.loadMonthlyOrderChart(view.getRevenueChartPanel());

      
        view.addLogoutListener(new LogoutListener(view));
        view.addOrderListener(new OrderListener());
        view.addBillListener(new BillListener());
        view.addReservationListener(new ReservationListener());
        view.addEventListener(new EventListener());
        view.addMenuListener(new MenuListener());
    }

    public static void loadRevenueChartFromDB(JPanel revenueChartPanel) {

    }
    public void setupLogoutListener(EmployeeDashboard view) {
        view.addLogoutListener(new LogoutListener(view));
    }

    void open() {
    EmployeeDashboard dashboard = new EmployeeDashboard();
    dashboard.setVisible(true);    }

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
    public void setupOrderListener(EmployeeDashboard view) {
        view.addOrderListener(new OrderListener());
    }
     
    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Order button clicked"); 
            OrderFrame orderView = new OrderFrame();
            new OrderController(orderView);
            orderView.setVisible(true);

            if (dashboardView != null) dashboardView.dispose();
        }
    }
    
    public void setupBillListener(EmployeeDashboard view) {
        view.addBillListener(new BillListener());
    }

     
    class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Bill button clicked"); 
            Bill billView = new Bill();
            new BillController(billView);
            billView.setVisible(true);

            if (dashboardView != null) dashboardView.dispose();
        }
    } 
    public void setupReservationListener(EmployeeDashboard view) {
        view.addReservationListener(new ReservationListener());
    }    
    class ReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("Reservation button clicked");
                Reservation reservationView = new Reservation();
                new ReservationController(reservationView);
                reservationView.setVisible(true);
                
                if (dashboardView != null) dashboardView.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(EDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void close() {
        
    }

    public void setupEventListener(EmployeeDashboard view) {
        view.addEventListener(new EventListener());
    }    
    class EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("EventBooking button clicked"); 
            EventBooking eventView = new EventBooking();
            new EventController(eventView);
            eventView.setVisible(true);

            if (dashboardView != null) dashboardView.dispose();
        }
    }
     public void setupMenuListener(EmployeeDashboard view) {
        view.addMenuListener(new MenuListener());
    }    
    class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ManageMenu button clicked"); 
            ManageMenu menuView = new ManageMenu();
            new MenuController(menuView);
            menuView.setVisible(true);

            if (dashboardView != null) dashboardView.dispose();
        }
    }
}

