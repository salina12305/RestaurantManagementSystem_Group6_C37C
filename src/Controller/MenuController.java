///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Controller;
//
//
//import Dao.MenuDAO;
////import Database.DBConnection;
//import java.sql.Connection;
//import java.awt.HeadlessException;
//import java.sql.SQLException;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import Database.MySqlConnection;
//
//public class MenuController {
//    
//   
//
//    public static void handleCreate(String id, String name, String count, String price, String empName) {
//        
//        MySqlConnection mysql = new MySqlConnection();
//        if ( name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        try {
////            int itemId = Integer.parseInt(id);
//            int itemCount = Integer.parseInt(count);
//            double itemPrice = Double.parseDouble(price);
////            Connection conn = (Connection) mysql.getConnection();
//            Connection conn = mysql.openConnection();
//
//            boolean success = MenuDAO.createMenu((java.sql.Connection) conn, name, itemCount, itemPrice, empName);
//            if (success) {
//                JOptionPane.showMessageDialog(null, "Order created successfully!");
//                new NextForm().setVisible(true);  // replace with your actual next interface
//            }
//        } catch (HeadlessException | NumberFormatException  e) {
//            JOptionPane.showMessageDialog(null, "Error creating order: " + e.getMessage());
//        }
//    }
//
////    public static void handleUpdate(String id, String name, String count, String price, String empName) {
////        if (id.isEmpty() || name.isEmpty() || count.isEmpty() || price.isEmpty() || empName.isEmpty()) {
////            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
////            return;
////        }
////
////        try {
////            int itemId = Integer.parseInt(id);
////            int itemCount = Integer.parseInt(count);
////            double itemPrice = Double.parseDouble(price);
//////            Connection conn = (Connection) DBConnection.getConnection();
////            boolean success = MenuDao.update((java.sql.Connection) conn, itemId, name, itemCount, itemPrice, empName);
////            if (success) {
////                JOptionPane.showMessageDialog(null, "Order updated successfully!");
////                new NextForm().setVisible(true);
////            }
////        } catch (HeadlessException | NumberFormatException | SQLException e) {
////            JOptionPane.showMessageDialog(null, "Error updating order: " + e.getMessage());
////        }
////    }
////
//    public static void handleDelete(String id) {
//        MySqlConnection mysql = new MySqlConnection();
////        if (id.isEmpty()) {
////            JOptionPane.showMessageDialog(null, "Please enter Item ID!", "Validation Error", JOptionPane.ERROR_MESSAGE);
////            return;
////        }
//
//        try {
//            int itemId = Integer.parseInt(id);
////            Connection conn = (Connection) DBConnection.getConnection();
//               Connection conn = mysql.openConnection();
//
//            boolean success = MenuDAO.deleteMenu((java.sql.Connection) conn, itemId);
//            if (success) {
//                JOptionPane.showMessageDialog(null, "Order deleted successfully!");
//                new NextForm().setVisible(true);
//            }
//        } catch (HeadlessException | NumberFormatException  e) {
//            JOptionPane.showMessageDialog(null, "Error deleting order: " + e.getMessage());
//        }
//    }
//
//    public static void create(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
//    }
//
//    public static void update(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
//    }
//
//    public static void deleteOrder(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
//    }
//
//    public static void delete(JTextField Username, JTextField ItemName, JTextField PriceOfItem, JTextField no_item, JTextField EmployeeName) {
//    }
//}




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
        menuView.addEventListener(new BillListener());
        menuView.addOrderListener(new OrderListener());
        menuView.addReservationListener(new ReservationListener());
        menuView.addLogoutListener(new LogoutListener(menuView));
        
    }

    void open() {
       
    }


    class CreateMenuListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            String itemName = menuView.getItemNameField().getText().trim();
            String noOfItemStr = menuView.getNoOfItemField().getText().trim();
            String priceStr = menuView.getPriceField().getText().trim();
            String employeeName = menuView.getEmployeeNameField().getText().trim();

            // ✅ Validate input
            if (itemName.isEmpty() || noOfItemStr.isEmpty() || priceStr.isEmpty() || employeeName.isEmpty()) {
                JOptionPane.showMessageDialog(menuView, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // ✅ Parse values after validation
            int noOfItem = Integer.parseInt(noOfItemStr);
            double price = Double.parseDouble(priceStr);

            // ✅ Create and save item
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



//    class UpdateOrderListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
//                String itemName = orderView.getItemNameField().getText();
//                int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
//                double price = Double.parseDouble(orderView.getPriceField().getText());
//                String customerName = orderView.getCustomerNameField().getText();
//                String employeeName = orderView.getEmployeeNameField().getText();
//
//                OrderModel order = new OrderModel(orderId, itemName, noOfItem, price, customerName, employeeName);
//                boolean success = orderDao.updateOrder(order);
//
//                if (success) {
//                    JOptionPane.showMessageDialog(orderView, "Order updated successfully!");
//                } else {
//                    JOptionPane.showMessageDialog(orderView, "Order ID not found. Update failed.");
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(orderView, "Failed to update order: " + ex.getMessage());
//            }
//        }
//    }

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

    public void setupBillListener(ManageMenu view) {
        view.addBillListener(new BillListener());
    }
     
    class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Bill button clicked"); 
            Bill billView = new Bill();
            new BillController(billView);
            billView.setVisible(true);

//            if (menuView != null) menuView.dispose();
menuView.dispose();
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
    public void setupDashboardListener(ManageMenu view) {
        view.addDashboardListener(new DashboardListener());
    }
     
    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Dashboard button clicked"); 
            EmployeeDashboard dashboardView = new EmployeeDashboard();
//            dashboardView.setVisible(true);
            
//            control.open();
            new EDashboardController(dashboardView);
            dashboardView.setVisible(true);
              menuView.dispose();
//            if (menuView != null) menuView.dispose();
        }
    }
      public void setupOrderListener(ManageMenu view) {
        view.addOrderListener(new OrderListener());
    }
     
    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Order button clicked"); 
            OrderFrame orderView = new OrderFrame();
            new OrderController(orderView);
            orderView.setVisible(true);

//            if (menuView != null) menuView.dispose();
//            if (menuView != null) menuView.dispose();
            menuView.dispose();
        }
    }
    public void setupReservationListener(ManageMenu view) {
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
                
//                if (menuView != null) menuView.dispose();
             //            if (menuView != null) menuView.dispose();
            menuView.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void setupEventListener(ManageMenu view) {
        view.addEventListener(new EventListener());
    }    
    class EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("EventBooking button clicked"); 
            EventBooking eventView = new EventBooking();
            new EventController(eventView);
            eventView.setVisible(true);

//            if (menuView != null) menuView.dispose();
        //            if (menuView != null) menuView.dispose();
       menuView.dispose();
        }
    }

//     class DashboardListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            EmployeeDashboard dashboardView = new EmployeeDashboard();
//            EDashboardController edashboard = new EDashboardController(dashboardView);
//
//            edashboard.open();
//          close();
//        }
//
//        private void close() {
//        }
//    }
}

