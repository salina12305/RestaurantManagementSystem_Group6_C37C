
package Controller;

import Dao.AuthDao;
import Model.SecAnswers;

import View.ResetPass;
import View.SecurityQuestion;
import View.SignIn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AuthController {

    private final AuthDao authdao= new AuthDao();

    private SecurityQuestion userView1;
    private ResetPass userView2;

    public AuthController(SecurityQuestion userView1){
        this.userView1 = userView1;
        this.userView1.addSubmitListener(new SubmitListener());
    }

    public AuthController(ResetPass userView2){
        this.userView2 = userView2;
        userView2.addContinueListener(new ContinueListener());
    }
    
    public void open() {
    if (userView1 != null) {
        userView1.setVisible(true);
    } else {
        System.out.println("No view to open.");
    }
}

    public boolean checkSecurityAnswers(String email, String[] answers){
        return authdao.validateSecurityAnswers(email, answers);
    }
    public boolean updatePassword(String email, String newPassword){
        return authdao.updatePassword(email,newPassword);
    }
  
//    }
    class SubmitListener implements ActionListener{
        
    

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("got it");

            String email = userView1.getEmailField().getText();
                String answer1 = userView1.getanswer1().getText();
                String answer2 = userView1.getanswer2().getText();
                String answer3 = userView1.getanswer3().getText();
                
            try{
                if(email.isEmpty() || answer1.isEmpty()|| answer2.isEmpty()|| answer3.isEmpty()){
                    JOptionPane.showMessageDialog(userView1,"Fill in all entry fields!.");
                return;
                }

//                
                if(userView1.getMode().equals("store")){
                    if (!email.toLowerCase().endsWith("@gmail.com")) {
               JOptionPane.showMessageDialog(null, "Only Gmail accounts are allowed.");
               return;
            }
                    if(!authdao.emailExists(email)){
                       JOptionPane.showMessageDialog(null, "Email not registered. Register first.");
               return; 
                    }
                    

                
                SecAnswers user = new SecAnswers(email,answer1,answer2,answer3);
                authdao.insertSecurityAnswers(user);

                
                JOptionPane.showMessageDialog(userView1,"Security questions stored successfully.");

                
                SignIn signin = new SignIn();
                signin.setVisible(true);
                SignInController controller = new SignInController(signin);
                controller.open();
                
                userView1.dispose();
                }else if (userView1.getMode().equals("verify")){
                    if(!authdao.emailExists(email)){
                        JOptionPane.showMessageDialog(userView1,"This email is not registered!");
                        return;
                    }
                  boolean valid = checkSecurityAnswers(email,new String[]{answer1,answer2,answer3});
                  if (valid){
                      JOptionPane.showMessageDialog(userView1,"Answers verified! Set your new password.");
                      ResetPass reset = new ResetPass(email);
                      reset.setVisible(true);
                      AuthController controller = new AuthController(reset);
                      controller.open();
                      userView1.dispose();
                  }else{
                      JOptionPane.showMessageDialog(userView1,"Incorrect answers. Try again!");
                  }
                }
            }catch (Exception ex) {
               JOptionPane.showMessageDialog(userView1,"Something went wrong:" + ex.getMessage());
                ex.printStackTrace();
            }

          
        }
    
}
    class ContinueListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = userView2.getNewPasswordField().trim();
            String confirmPassword = userView2.getConfirmPasswordField().trim();
            if (!newPassword.equals(confirmPassword)){
                JOptionPane.showMessageDialog(userView2,"Password do not match!");
                return;
            }
            String email = userView2.getEmail();
            boolean success = updatePassword(email,newPassword);
            if(success){
               JOptionPane.showMessageDialog(userView2,"Password updated!"); 
               new SignIn().setVisible(true);
               userView2.dispose();
            }else{
               JOptionPane.showMessageDialog(userView2,"Failed to update!");  
            }
        }
        
    }

    
}