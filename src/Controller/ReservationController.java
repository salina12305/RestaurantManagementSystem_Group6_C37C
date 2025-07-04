 package Controller;

import Dao.EventDao;
import Dao.ReservationDao;
import Model.ReservationModel;
import View.Bill;
import View.OrderFrame;
import View.Reservation;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.EventBooking;
import View.ManageMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Set;

public class ReservationController {
 private final ReservationDao reservationDao = new ReservationDao();
 private final Reservation reservationView;
  private EventDao eventDao;
  
    
    public ReservationController(Reservation reservationView) throws java.sql.SQLException {
          this.reservationView = reservationView; 
          this.eventDao = new EventDao();
          loadBookingStatus();
          

        reservationView.addDashboardListener(new DashboardListener());
        reservationView.addBillListener(new BillListener());
        reservationView.addOrderListener(new OrderListener());
        reservationView.addMenuListener(new MenuListener());
        reservationView.addEventListener(new EventListener());
        reservationView.addLogoutListener(new LogoutListener(reservationView));
        
    }
    
     public void loadBookingStatus() throws java.sql.SQLException {
Set<Integer> bookedFloors = eventDao.getBookedFloors();
// Loop through 1 to 9 floors
for (int i = 1; i <= 9; i++) {
String status = bookedFloors.contains(i) ? "Booked" : "Available";
reservationView.setRoomStatus(i, status);  // Delegates display logic to view
}
// Update Staff Table
List<String[]> staffRows = eventDao.getStaffDuty();
DefaultTableModel model = reservationView.getStaffTableModel();
model.setRowCount(0);
for (String[] row : staffRows) {
model.addRow(row);
}
   
    }

    public void setupLogoutListener(EmployeeDashboard view) {
        view.addLogoutListener(new LogoutListener(view));
    }

//public void open() {
//    System.out.println("Opening Reservation View...");
//    try {
//        Reservation newReservationView = new Reservation();
//        new ReservationController(newReservationView);  // Initialize controller
//        newReservationView.setVisible(true);
//        if (reservationView != null) reservationView.dispose(); // Dispose old if needed
//    } catch (Exception ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Error opening Reservation view: " + ex.getMessage());
//    }
//}
    public void open() {
        this.reservationView.setVisible(true);
    }

    public void close() {
        this.reservationView.dispose();
    }


    private static class SQLException {

        public SQLException() {
        }
    }

   //for logout button 
    class LogoutListener implements ActionListener {
        private JFrame currentFrame;

        public LogoutListener(JFrame frame) {
            this.currentFrame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                currentFrame.dispose(); 
                new EmployeeSignIn().setVisible(true); 
            }
        }
    }
//    public void setupBillListener(Reservation view) {
//        view.addBillListener(new BillListener());
//    }
//     
//    class BillListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Bill button clicked"); 
//            Bill billView = new Bill();
//            new BillController(billView);
//            billView.setVisible(true);
//            if (reservationView != null) reservationView.dispose();
//        }
//    }
     class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Bill billView = new Bill();
            billView.setVisible(true);
            BillController controller = new BillController(billView);
            System.out.println("BillController  Clicked");
            controller.open();
            close();
        }
    }
//    public void setupDashboardListener(Reservation view) {
//        view.addDashboardListener(new DashboardListener());
//    }
//     
//    class DashboardListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Dashboard button clicked"); 
//            EmployeeDashboard dashboardView = new EmployeeDashboard();
//            new EDashboardController(dashboardView);
//            dashboardView.setVisible(true);
//
//            if (reservationView != null) reservationView.dispose();
//        }
//    }
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
//    public void setupMenuListener(Reservation view) {
//        view.addMenuListener(new MenuListener());
//    }
//     
//    class MenuListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("ManageMenu button clicked"); 
//            ManageMenu menuView = new ManageMenu();
//            new MenuController(menuView);
//            menuView.setVisible(true);
//            if (reservationView != null) reservationView.dispose();
//        }
//    }
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
 //   public void setupEventListener(Reservation view) {
//        view.addEventListener(new EventListener());
//    }
//     
//    class EventListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Event button clicked"); 
//            EventBooking eventView = new EventBooking();
//            new EventController(eventView);
//            eventView.setVisible(true);
//            
//            if (reservationView != null) reservationView.dispose();
//        }
//    }
    
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
    
//    public void setupOrderListener(Reservation view) {
//        view.addMenuListener(new OrderListener());
//    }
//     
//    class OrderListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Order button clicked"); 
//            OrderFrame orderView = new OrderFrame();
//            new OrderController(orderView);
//            orderView.setVisible(true);
//            
//           if (reservationView != null) reservationView.dispose();
//        }
//    }
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
}


