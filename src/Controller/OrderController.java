
package Controller;

import Dao.OrderDao;
import Model.OrderModel;
import View.OrderFrame;
import Dao.BillDao;
import Model.BillModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderController {
    private final OrderDao orderDao = new OrderDao();
    private final OrderFrame orderView;  
    public OrderController(OrderFrame view) {
        this.orderView = view;  

     
        orderView.addCreateListerner(new CreateOrderListener());
        orderView.addUpdateListerner(new UpdateOrderListener());
        orderView.addDeleteListerner(new DeleteOrderListener());
    }

    void open() {
       
    }

//    class CreateOrderListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
//                String itemName = orderView.getItemNameField().getText();
//                int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
//                double price = Double.parseDouble(orderView.getPriceField().getText());
//                String customerName = orderView.getCustomerNameField().getText();
//                String employeeName = orderView.getEmployeeNameField().getText();
//
//                OrderModel order = new OrderModel(orderId,itemName, noOfItem, price, customerName, employeeName);
//                orderDao.createOrder(order);
//
//                JOptionPane.showMessageDialog(orderView, "Order created successfully!");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(orderView, "Failed to create order: " + ex.getMessage());
//            }
//        }
//    }
    
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

            // 💡 Generate and save corresponding Bill
            double amount = price * noOfItem;
            double discount = 0.0;  // or add logic
            double tax = amount * 0.13; // e.g. 13% VAT
            double total = amount + tax - discount;

            BillModel bill = new BillModel(
                0, itemName, noOfItem, price,
                amount, discount, tax, total, customerName
            );

            new BillDao().saveBill(bill); // Save bill

            JOptionPane.showMessageDialog(orderView, "Order and Bill created successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(orderView, "Failed to create order: " + ex.getMessage());
        }
    }
}


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

    public void setupLogoutListener() {
        orderView.addLogoutListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logout clicked");
            }
        });
    }
}

