//
//package Controller;
//
//import View.EmployeeDashboard;
//import View.EmployeeSignIn;
//import View.SignIn;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//
//public class ReservationController extends javax.swing.JFrame {
//
//    
//    public ReservationController() {
//        initComponents();
//        
//        ReservationController controller = new ReservationController();
//        controller.setupLogoutListener(this);
//        
//
//        
//    }
//
//    public void open() {
//        
//    }
//
//    public void close() {
//        
//    }
//    public void setupLogoutListener(EmployeeDashboard view) {
//    view.addLogoutListener(new LogoutListener(view));
//}
//    
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
//}

 package Controller;

import View.EmployeeDashboard;
import View.EmployeeSignIn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReservationController {

    
    public ReservationController() {
       
    }


    public void setupLogoutListener(EmployeeDashboard view) {
        view.addLogoutListener(new LogoutListener(view));
    }


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
                currentFrame.dispose(); // Close current dashboard
                new EmployeeSignIn().setVisible(true); // Show sign-in screen
            }
        }
    }
   
}


