
package Controller;

import Dao.EDashboardDao;
import Database.MySqlConnection;
import View.Bill;
import View.EmployeeDashboard;
import View.OrderFrame;
import View.EmployeeSignIn; 
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.time.Month;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import View.EmployeeDashboard;
import View.EmployeeSignIn; 
import View.Reservation;
import View.SecurityQuestion;
import View.SignIn;
import View.SignUpp;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;


public class EDashboardController {

    public static void loadRevenueChart(JPanel panel, JFrame frame) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();



    JFreeChart chart = ChartFactory.createBarChart(
        "Monthly Revenue", "Month", "Revenue", dataset,
        PlotOrientation.VERTICAL, false, true, false
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    panel.removeAll();
    panel.add(chartPanel, BorderLayout.CENTER);
    panel.validate();
}
//     private void close() {
//         
//        }
//     private void open(){
//         
//     }
     



    MySqlConnection mysql = new MySqlConnection();
//    private EmployeeDashboard dashboardView;
//    private JFrame userView;
//    private OrderFrame orderView;
//    private Bill billView;
//    
    private final EDashboardDao edashboardDao = new EDashboardDao(); 
    private final EmployeeDashboard dashboardView;

    public EDashboardController(EmployeeDashboard view) {
        this.dashboardView=view;
        
        EDashboardDao.loadRevenueChartFromDB(view.getRevenueChartPanel());
      
        view.addLogoutListener(new LogoutListener(view));
        view.addOrderListener(new OrderListener());
        view.addBillListener(new BillListener());
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
    
    private void close() {
        
    }   
    


//    public void setupLogoutListener(EmployeeDashboard view) {
//    view.addLogoutListener(new LogoutListener(view));
//}
    
//    class LogoutListener implements ActionListener {
//    private JFrame currentFrame;
//
//    public LogoutListener(JFrame frame) {
//        this.currentFrame = frame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int confirm = JOptionPane.showConfirmDialog(null,
//                "Are you sure you want to logout?",
//                "Logout Confirmation",
//                JOptionPane.YES_NO_OPTION);
//
//        if (confirm == JOptionPane.YES_OPTION) {
//            currentFrame.dispose(); // 
//            new EmployeeSignIn().setVisible(true); 
//        }
//    }
//}
    
    public void setupReservationListener(EmployeeDashboard view) {
    view.addReservationListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Reservation reservationView = new Reservation();
            reservationView.setVisible(true);
        }
    });
}
    
//    public class SomeOtherController {
//    private JFrame currentFrame; // your current frame
//
//    public SomeOtherController(JFrame currentFrame) {
//        this.currentFrame = currentFrame;
//    }
//
//    class OpenManageMenuListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            ManageMenu manageMenu = new ManageMenu();
//            new MenuController(manageMenu);  // attach listeners
//            manageMenu.setVisible(true);
//            currentFrame.dispose();  // close current window if needed
//        }
//    }
//}

    
// class ReservationListener implements ActionListener {
//    private JFrame currentFrame;
//
//    public ReservationListener(JFrame frame) {
//        this.currentFrame = frame;
//    }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Reservation  = new Reservation();
//            view.setVisible(true);
//            view.open();
//            close();
//        }
//    }


}

