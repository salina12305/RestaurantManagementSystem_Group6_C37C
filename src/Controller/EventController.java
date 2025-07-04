
package Controller;

import Dao.EventDao;
import Model.EventModel;
import View.Bill;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.EventBooking;
import View.ManageMenu;
import View.OrderFrame;
import View.Reservation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventController {
    private final EventDao eventDao = new EventDao();
    private final EventBooking eventView;

    public EventController(EventBooking eventView) {
        this.eventView = eventView;
        

        eventView.addCreateListerner(new CreateEventListener());
        eventView.addUpdateListerner(new UpdateEventListener());
        eventView.addDeleteListerner(new CancelEventListener());
        eventView.addDashboardListener(new DashboardListener());
        eventView.addBillListener(new BillListener());
        eventView.addLogoutListener(new LogoutListener(eventView));
        eventView.addReservationListener(new ReservationListener());
        eventView.addMenuListener(new MenuListener());
        eventView.addOrderListener(new OrderListener());
    }
    
    public void open() {
        this.eventView.setVisible(true);
    }

    public void close() {
        this.eventView.dispose();
    }

    class CreateEventListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            String customerName = eventView.getCustomerNameField().getText();
            String event = eventView.getEventField().getText();
            String dateText = eventView.getDateField().getText(); 
            String staff = eventView.getAssignedStaffField().getText();
            String floorText = eventView.getFloorField().getText(); 

            // Validation
            if (customerName.isEmpty() || event.isEmpty() || dateText.isEmpty() || staff.isEmpty() || floorText.isEmpty()) {
                JOptionPane.showMessageDialog(eventView, "Please fill in all fields.");
                return;
            }

            // Date format check
            java.sql.Date date;
            try {
                date = java.sql.Date.valueOf(dateText); // must be yyyy-MM-dd
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(eventView, "Invalid date format. Use yyyy-MM-dd.");
                return;
            }

            // Floor number check
            int floor;
            try {
                floor = Integer.parseInt(floorText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(eventView, "Floor must be a number.");
                return;
            }

            // Valid data — Create event
            EventModel model = new EventModel(customerName, event, date, staff, floor);
            eventDao.createEvent(model);
            JOptionPane.showMessageDialog(eventView, "Event created successfully.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
        }
    }
}


    class UpdateEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to update:"));

                String customerName = eventView.getCustomerNameField().getText();
                String event = eventView.getEventField().getText();
                java.sql.Date date = java.sql.Date.valueOf(eventView.getDateField().getText());
                String staff = eventView.getAssignedStaffField().getText();
                int floor = Integer.parseInt(eventView.getFloorField().getText());

                EventModel model = new EventModel(customerName, event, date, staff, floor);
                boolean updated = eventDao.updateEvent(model, eventId);

                if (updated) {
                    JOptionPane.showMessageDialog(eventView, "Event updated.");
                } else {
                    JOptionPane.showMessageDialog(eventView, "Update failed. ID not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
            }
        }
    }

    class CancelEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to cancel:"));
                boolean deleted = eventDao.deleteEvent(eventId);

                if (deleted) {
                    JOptionPane.showMessageDialog(eventView, "Event cancelled.");
                } else {
                    JOptionPane.showMessageDialog(eventView, "Cancel failed. ID not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
            }
        }
    }
//    public void setupBillListener(EventBooking view) {
//        view.addBillListener(new BillListener());
//    }
//     
//    class BillListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Bill button clicked"); 
//            Bill billView = new Bill();
////            new BillController(billView);
//            billView.setVisible(true);
//            if (eventView != null) eventView.dispose();
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


    public void setupLogoutListener(EventBooking view) {
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
//    public void setupDashboardListener(EventBooking view) {
//        view.addDashboardListener(new DashboardListener());
//    }
//     
//    class DashboardListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Dashboard button clicked"); 
//            EmployeeDashboard dashboardView = new EmployeeDashboard();
////            new EDashboardController(dashboardView);
//            dashboardView.setVisible(true);
//            if (eventView != null) eventView.dispose();
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
//    public void setupReservationListener(EventBooking view) {
//        view.addReservationListener(new ReservationListener());
//    }
//     
//    class ReservationListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                System.out.println("Reservation button clicked");
//                Reservation reservationView = new Reservation();
////                new ReservationController(reservationView);
//                reservationView.setVisible(true);            
//                if (eventView != null) eventView.dispose();
//            } catch (SQLException ex) {
//                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    public void setupMenuListener(EventBooking view) {
//        view.addMenuListener(new MenuListener());
//    }
//     
//    class MenuListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Menu button clicked"); 
//            ManageMenu menuView = new ManageMenu();
////            new MenuController(menuView);
//            menuView.setVisible(true);
//            if (eventView != null) eventView.dispose();
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

//    public void setupOrderListener(EventBooking view) {
//        view.addOrderListener(new OrderListener());
//    }
//     
//    class OrderListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Order button clicked"); 
//            OrderFrame orderView = new OrderFrame();
////            new OrderController(orderView);
//            orderView.setVisible(true);
//
//            if (eventView != null) eventView.dispose();
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
//    public void start() {
//        Reservation reservationView = new Reservation();
//        ReservationController reservationController = new ReservationController(reservationView);
//
//        reservationView.addEventListener(e -> {
//            reservationView.dispose(); // close current window
//            openEventBooking();
//        });
//
//        reservationView.setVisible(true);
//    }
//     private void openEventBooking() {
//        EventBooking eventView = new EventBooking();
//        EventController eventController = new EventController(eventView); // assuming it exists
//
//        eventView.addReservationListener(e -> {
//            eventView.dispose(); // close event window
//            start(); // return to reservation view
//        });
//
//        eventView.setVisible(true);
//    }
}
