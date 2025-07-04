
package Controller;



import View.OrderFrame;
import Dao.BillDao;
import Dao.MenuDAO;
import Model.BillModel;
import Model.MenuItemModel;
import View.Bill;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.EventBooking;
import View.ManageMenu;
import View.Reservation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {
    private final MenuDAO menuDao = new MenuDAO();
    private final ManageMenu menuView;  
    
    public MenuController(ManageMenu menuView) {
        this.menuView = menuView; 
        

        menuView.addDashboardListener(new DashboardListener());
        menuView.addCreateListerner(new CreateMenuListener());
//        menuView.addUpdateListerner(new UpdateOrderListener());
        menuView.addDeleteListerner(new DeleteMenuListener());
        menuView.addBillListener(new BillListener());
        menuView.addEventListener(new EventListener());
        menuView.addOrderListener(new OrderListener());
        menuView.addReservationListener(new ReservationListener());
        menuView.addLogoutListener(new LogoutListener(menuView));
        
    }

    public void open() {
        this.menuView.setVisible(true);
    }

    public void close() {
        this.menuView.dispose();
    }


    class CreateMenuListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            String itemName = menuView.getItemNameField().getText().trim();
            String noOfItemStr = menuView.getNoOfItemField().getText().trim();
            String priceStr = menuView.getPriceField().getText().trim();
            String employeeName = menuView.getEmployeeNameField().getText().trim();

            //  Validate input
            if (itemName.isEmpty() || noOfItemStr.isEmpty() || priceStr.isEmpty() || employeeName.isEmpty()) {
                JOptionPane.showMessageDialog(menuView, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //  Parse values after validation
            int noOfItem = Integer.parseInt(noOfItemStr);
            double price = Double.parseDouble(priceStr);

            //  Create and save item
            MenuItemModel menu = new MenuItemModel(itemName, noOfItem, price, employeeName);
            menuDao.createMenuItem(menu);

            JOptionPane.showMessageDialog(menuView, "Menu created successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(menuView, "Please enter valid numbers for Quantity and Price.", "Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menuView, "Failed to create order: " + ex.getMessage());
        }
    }
}

    class DeleteMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String itemName = menuView.getItemNameField().getText(); 

                boolean success = menuDao.deleteMenu(itemName);

                if (success) {
                    JOptionPane.showMessageDialog(menuView, "Menu Item deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(menuView, "Menu Item not found. Delete failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menuView, "Failed to delete order: " + ex.getMessage());
            }
        }
    }

//    public void setupBillListener(ManageMenu view) {
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
//            if (menuView != null) menuView.dispose();
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


    public void setupLogoutListener(ManageMenu view) {
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
//    public void setupDashboardListener(ManageMenu view) {
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
//
//            if (menuView != null) menuView.dispose();
//        }
//    }
    
//    public void setupOrderListener(ManageMenu view) {
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
//            if (menuView != null) menuView.dispose();
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
//   
//    public void setupReservationListener(ManageMenu view) {
//        view.addReservationListener(new ReservationListener());
//    }    
//    class ReservationListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                System.out.println("Reservation button clicked");
//                Reservation reservationView = new Reservation();
////                new ReservationController(reservationView);
//                reservationView.setVisible(true);
//                if (menuView != null) menuView.dispose();
//            } catch (SQLException ex) {
//                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
//    public void setupEventListener(ManageMenu view) {
//        view.addEventListener(new EventListener());
//    }    
//    class EventListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("EventBooking button clicked"); 
//            EventBooking eventView = new EventBooking();
////            new EventController(eventView);
//            eventView.setVisible(true);
//
//            if (menuView != null) menuView.dispose();
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
}

