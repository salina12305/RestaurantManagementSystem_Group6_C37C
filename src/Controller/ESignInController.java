    
package Controller;

import Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.User;
import View.SignIn;
import View.SignUpp;
import View.EmployeeSignIn;
import Model.ESigninRequest;
import View.EmployeeDashboard;
import View.AdminDashboard;
import View.SecurityQuestion;

public class ESignInController {
    private final UserDao userDao = new UserDao();
    private final EmployeeSignIn userView;

    public ESignInController(EmployeeSignIn userView) {
        this.userView = userView;
        userView.addRegisterListener(new RegisterListener());
        userView.addLoginUserListener(new LoginUserListener());
        userView.addAdminLoginListener(new AdminLoginListener());
        userView.addForgotListener(new ForgotListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignUpp signup = new SignUpp();
            signup.setVisible(true);
            SignUpController controller = new SignUpController(signup, null);
            controller.open();
            close();
        }
    }

    class AdminLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignIn admin = new SignIn();
            admin.setVisible(true);
            SignInController controller = new SignInController(admin);
            controller.open();
            close();
        }
    }

    class LoginUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                ESigninRequest loginRequest = new ESigninRequest(email, password);
                User employee = userDao.esignIn(loginRequest);

                if (employee == null) {
                    JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                } else {
                    JOptionPane.showMessageDialog(userView, "Login Successful");
                    

                    String role = employee.getRole();
                    System.out.println("Logged-in role: " + role);

                    if ("employee".equalsIgnoreCase(role)) {

                        EmployeeDashboard dashboard = new EmployeeDashboard();
                        dashboard.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(userView, "Unknown role");
                        return;
                    }

                    close();
                }

            } catch (Exception ex) {
                System.out.println("Login error: " + ex.getMessage());
                JOptionPane.showMessageDialog(userView, "An error occurred during login.");
            }
        }
    }
    class ForgotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = userView.getEmailField().getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(userView, "Please enter your email!");
                return;
            }
            SecurityQuestion sec = new SecurityQuestion("verify", email);
            sec.setVisible(true);
            AuthController controller = new AuthController(sec);
            controller.open();
            userView.dispose();
        }
    }
}

 