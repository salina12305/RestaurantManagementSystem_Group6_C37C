package Controller;

import Dao.OrderDao;
import Model.Order;
import View.OrderFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OrderController {
    private final OrderDao orderDao = new OrderDao();
    private final OrderFrame orderView;

    public OrderController(OrderFrame orderView) {
        this.orderView = orderView;

        orderView.addCreateOrderListener(new CreateOrderListener());
        orderView.addUpdateOrderListener(new UpdateOrderListener());
        orderView.addDeleteOrderListener(new DeleteOrderListener());
    }

    // === Create Order ===
    class CreateOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("CreateOrder button clicked"); 
            try {
                String itemName = orderView.getItemNameField().getText();
                int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
                double price = Double.parseDouble(orderView.getPriceField().getText());
                String customerName = orderView.getCustomerNameField().getText();
                String employeeName = orderView.getEmployeeNameField().getText();

                Order order = new Order(itemName, noOfItem, price, customerName, employeeName);
                orderDao.createOrder(order);

                JOptionPane.showMessageDialog(orderView, "Order created successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(orderView, " Failed to create order: " + ex.getMessage());
            }
        }
    }

    // === Update Order ===
    class UpdateOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
                String itemName = orderView.getItemNameField().getText();
                int noOfItem = Integer.parseInt(orderView.getNoOfItemField().getText());
                double price = Double.parseDouble(orderView.getPriceField().getText());
                String customerName = orderView.getCustomerNameField().getText();
                String employeeName = orderView.getEmployeeNameField().getText();

                Order order = new Order(orderId, itemName, noOfItem, price, customerName, employeeName);
                orderDao.updateOrder(order);

                JOptionPane.showMessageDialog(orderView, " Order updated successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(orderView, " Failed to update order: " + ex.getMessage());
            }
        }
    }

    // === Delete Order ===
    class DeleteOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int orderId = Integer.parseInt(orderView.getOrderIdField().getText());
                orderDao.deleteOrder(orderId);

                JOptionPane.showMessageDialog(orderView, "🗑️ Order deleted successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(orderView, " Failed to delete order: " + ex.getMessage());
            }
        }
    }
}
