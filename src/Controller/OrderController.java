
package Controller;

import Dao.OrderDao;
import Model.OrderModel;
import View.OrderFrame;
import Dao.BillDao;
import Model.BillModel;
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

public class OrderController {
    private final OrderDao orderDao = new OrderDao();
    private final OrderFrame orderView;  
    
    public OrderController(OrderFrame orderView) {
        this.orderView = orderView;  

        orderView.addDashboardListener(new DashboardListener());
        orderView.addCreateListerner(new CreateOrderListener());
        orderView.addUpdateListerner(new UpdateOrderListener());
        orderView.addDeleteListener(new DeleteOrderListener());
        orderView.addBillListener(new BillListener());
        orderView.addMenuListener(new MenuListener());
        orderView.addEventListener(new EventListener());
        orderView.addReservationListener(new ReservationListener());
        orderView.addLogoutListener(new LogoutListener(orderView));
        
    }

    public void open() {
        this.orderView.setVisible(true);
    }

    public void close() {
        this.orderView.dispose();
    }


    class CreateOrderListener implements ActionListener {
        
    public void actionPerformed(ActionEvent e) {
        try {
            int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
            String itemName = orderView.getItemNameField().getText();
            int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
            double price = Double.parseDouble(orderView.getPriceField().getText());
            String customerName = orderView.getCustomerNameField().getText();
            String employeeName = orderView.getEmployeeNameField().getText();

            // Save Order
            OrderModel order = new OrderModel(orderId, itemName, noOfItem, price, customerName, employeeName);
             orderDao.createOrder(order);

            //  Generate and save corresponding Bill
            double amount = price * noOfItem;
            double discount = 0.0;  // or add logic
            double tax = amount * 0.13; // e.g. 13% VAT
            double total = amount + tax - discount;

            BillModel bill = new BillModel(
                0, itemName, noOfItem, price,
                amount, discount, tax, total, customerName
            );

            new BillDao().saveBill(bill); // Save bill

            JOptionPane.showMessageDialog(orderView, "Order created successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(orderView, "Failed to create order: " + ex.getMessage());
        }
    }
}

    //for update order
    class UpdateOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
                String itemName = orderView.getItemNameField().getText();
                int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
                double price = Double.parseDouble(orderView.getPriceField().getText());
                String customerName = orderView.getCustomerNameField().getText();
                String employeeName = orderView.getEmployeeNameField().getText();

                OrderModel order = new OrderModel(orderId, itemName, noOfItem, price, customerName, employeeName);
                boolean success = orderDao.updateOrder(order);

                if (success) {
                    JOptionPane.showMessageDialog(orderView, "Order updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(orderView, "Order ID not found. Update failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(orderView, "Failed to update order: " + ex.getMessage());
            }
        }
    }
    //for delete order
    class DeleteOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
                boolean success = orderDao.deleteOrder(orderId);

                if (success) {
                    JOptionPane.showMessageDialog(orderView, "Order deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(orderView, "Order ID not found. Delete failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(orderView, "Failed to delete order: " + ex.getMessage());
            }
        }
    }

//    public void setupBillListener(OrderFrame view) {
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
//            if (orderView != null) orderView.dispose();
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


    public void setupLogoutListener(OrderFrame view) {
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
//    public void setupDashboardListener(OrderFrame view) {
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
//            if (orderView != null) orderView.dispose();
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
    
//    public void setupReservationListener(OrderFrame view) {
//        view.addReservationListener(new ReservationListener());
//    }    
//    class ReservationListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                System.out.println("Reservation button clicked");
//                Reservation reservationView = new Reservation();
//                new ReservationController(reservationView);
//                reservationView.setVisible(true);
//                if (orderView != null) orderView.dispose();
//            } catch (SQLException ex) {
//                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
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
 
//    public void setupEventListener(OrderFrame view) {
//        view.addEventListener(new EventListener());
//    }    
//    class EventListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("EventBooking button clicked"); 
//            EventBooking eventView = new EventBooking();
//            new EventController(eventView);
//            eventView.setVisible(true);
//            if (orderView != null) orderView.dispose();
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
//    public void setupMenuListener(OrderFrame view) {
//        view.addMenuListener(new MenuListener());
//    }    
//    class MenuListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("ManageMenu button clicked"); 
//            ManageMenu menuView = new ManageMenu();
//            new MenuController(menuView);
//            menuView.setVisible(true);
//            if (orderView != null) orderView.dispose();
//        }
//    } 
}

