
package Controller;

import Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.User;
import View.SignIn;
import Model.SigninRequest;
import View.AdminDashboard;
import View.EmployeeDashboard;
import View.EmployeeSignIn;
import View.SecurityQuestion;
import View.SignUpp;

public class SignInController {
    private final UserDao userDao = new UserDao();
    private final SignIn userView;

    public SignInController(SignIn userView) {
        this.userView = userView;
        userView.addLoginUserListener(new AddLoginListener());
        userView.addForgotListener(new ForgotListener());
        userView.addRegisterListener(new RegisterListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AddLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = userView.getEmailField().getText().trim();
                String password = userView.getPasswordField().getText().trim();

                SigninRequest loginRequest = new SigninRequest(email, password);
                User signInUser = userDao.signIn(loginRequest);

                if (signInUser == null) {
                    JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                } else {
                    System.out.println("Logged-in role: " + signInUser.getRole());
                    JOptionPane.showMessageDialog(userView, "Login Successfully");

                    // Use role from database
                    String role = signInUser.getRole();
                    if ("admin".equalsIgnoreCase(role)) {
                        AdminDashboard dashboard = new AdminDashboard();
                        dashboard.setVisible(true);


                    } else {
                        JOptionPane.showMessageDialog(userView, "Unknown role. Contact admin.");
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
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignUpp signup = new SignUpp();
            signup.setVisible(true);
            signup.open();
            close();
        }
    }
    class ELoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EmployeeSignIn signinView = new EmployeeSignIn();
            ESignInController signin = new ESignInController(signinView);

            signin.open();
            close();
        }
    }
}


