
package Controller;

import Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.User;
import View.SecurityQuestion;
import View.SignUpp;
import View.SignIn;

public class SignUpController {
    private final UserDao userDao = new UserDao();
    private final SignUpp userView;
    private final String role;
    
    public SignUpController(SignUpp userView, java.lang.String role) {
        this.userView = userView;
        userView.addAddUserListener(new AddUserListener());
        userView.addLoginListener(new LoginListener());
        this.role = role;
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String company_name = userView.getCompanyNameField().getText();
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                String confirm_password = userView.getConfrimPassword().getText();

                //  Assign a role — can be "admin" or "employee"
                 String role;
                if (email.equalsIgnoreCase("admin@gmail.com")) {
                    role = "admin";
                } else {
                    role = "employee";
                }

                User user = new User(company_name, email, password, confirm_password, role);

                boolean check = userDao.CheckUser(user);
                if (check) {
                    JOptionPane.showMessageDialog(null, "User already exists.");
                } else if (!email.toLowerCase().endsWith("@gmail.com")) {
                    JOptionPane.showMessageDialog(null, "Only Gmail accounts are allowed.");
                } else if (!"7-11".equals(company_name)) {
                    JOptionPane.showMessageDialog(null, "Company name must be '7-11'.");
                } else if (email == null || email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email cannot be empty.");
                } else if (password == null || password.trim().isEmpty() || password.equals("Set Password")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid password.");
                } else if (!password.equals(confirm_password)) {
                    JOptionPane.showMessageDialog(null, "Confirm password didn't match!");
                } else {
                    userDao.UserDao(user);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    userView.dispose();

                    SecurityQuestion reset = new SecurityQuestion("store", email);
                    reset.setVisible(true);
                    AuthController control = new AuthController(reset);
                    control.open();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignIn loginView = new SignIn();
            SignInController login = new SignInController(loginView);

            login.open();
            close();
        }
    }
}

