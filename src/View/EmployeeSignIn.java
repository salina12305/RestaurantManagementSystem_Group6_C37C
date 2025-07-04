
package View;

import Controller.EDashboardController;
import java.awt.Color;
import java.awt.event.ActionListener;

public class EmployeeSignIn extends javax.swing.JFrame {

    /**
     * Creates new form EmployeeSignIn
     */
    public EmployeeSignIn() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSignIn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextPass = new javax.swing.JTextField();
        jTextEmail = new javax.swing.JTextField();
        btnforgot = new javax.swing.JButton();
        jSignup = new javax.swing.JButton();
        AdminLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 228, 201));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 8)); // NOI18N
        jLabel5.setText("Don’t have an account ?");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel1.setText("From front desk to top tier - watch the glow-up");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel2.setText("Missed us, didn’t you?");

        jSignIn.setBackground(new java.awt.Color(78, 31, 0));
        jSignIn.setForeground(new java.awt.Color(255, 228, 201));
        jSignIn.setText("SIGN IN");
        jSignIn.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jSignInComponentMoved(evt);
            }
        });
        jSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignInActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Jaini Purva", 1, 36)); // NOI18N
        jLabel4.setText("7 ~ 11");

        jTextPass.setBackground(new java.awt.Color(255, 228, 201));
        jTextPass.setFont(new java.awt.Font("Jaini Purva", 0, 10)); // NOI18N
        jTextPass.setText(" Password");
        jTextPass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 0, 0), 1, true));
        jTextPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPassFocusLost(evt);
            }
        });
        jTextPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPassActionPerformed(evt);
            }
        });

        jTextEmail.setBackground(new java.awt.Color(255, 228, 201));
        jTextEmail.setFont(new java.awt.Font("Jaini Purva", 0, 10)); // NOI18N
        jTextEmail.setText(" Email");
        jTextEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 0, 0), 1, true));
        jTextEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextEmailFocusLost(evt);
            }
        });
        jTextEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextEmailActionPerformed(evt);
            }
        });

        btnforgot.setBackground(new java.awt.Color(255, 228, 201));
        btnforgot.setFont(new java.awt.Font("Times New Roman", 0, 8)); // NOI18N
        btnforgot.setText("Forget Password?");
        btnforgot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnforgotActionPerformed(evt);
            }
        });

        jSignup.setBackground(new java.awt.Color(255, 228, 201));
        jSignup.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jSignup.setText("SignUp");
        jSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignupActionPerformed(evt);
            }
        });

        AdminLogin.setBackground(new java.awt.Color(255, 228, 201));
        AdminLogin.setFont(new java.awt.Font("Jaini Purva", 1, 10)); // NOI18N
        AdminLogin.setText("Admin Login");
        AdminLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminLoginActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/side.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnforgot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSignIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSignup)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(AdminLogin)
                                .addGap(51, 51, 51)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)))))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnforgot, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSignIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSignup)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(AdminLogin)
                .addGap(21, 21, 21))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSignInComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSignInComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jSignInComponentMoved

    private void jSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignInActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jSignInActionPerformed

    private void jTextPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPassFocusGained
        // TODO add your handling code here:
        if (jTextPass.getText().equals(" Password")){
            jTextPass.setText("");
            jTextPass.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextPassFocusGained

    private void jTextPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPassFocusLost
        // TODO add your handling code here:
        if (jTextPass.getText().isEmpty()){
            jTextPass.setText(" Password");
            jTextPass.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextPassFocusLost

    private void jTextPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPassActionPerformed

    private void jTextEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEmailFocusGained
        // TODO add your handling code here:
        if (jTextEmail.getText().equals(" Email")){
            jTextEmail.setText("");
            jTextEmail.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextEmailFocusGained

    private void jTextEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEmailFocusLost
        // TODO add your handling code here:
        if (jTextEmail.getText().isEmpty()){
            jTextEmail.setText(" Email");
            jTextEmail.setForeground(Color.GRAY);

        }
    }//GEN-LAST:event_jTextEmailFocusLost

    private void jSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignupActionPerformed
        // TODO add your handling code here:
//        this.dispose();
//        SignUpp sn = new SignUpp();
//        sn.setVisible(true);
    }//GEN-LAST:event_jSignupActionPerformed

    private void AdminLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminLoginActionPerformed

    }//GEN-LAST:event_AdminLoginActionPerformed

    private void jTextEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextEmailActionPerformed

    private void btnforgotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnforgotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnforgotActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeSignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminLogin;
    private javax.swing.JButton btnforgot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jSignIn;
    private javax.swing.JButton jSignup;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextPass;
    // End of variables declaration//GEN-END:variables

public void addRegisterListener(ActionListener listener) {
//    System.out.println("register");
        jSignup.addActionListener(listener);
    }

public void addLoginUserListener(ActionListener listener) {
//    System.out.println("login");
        jSignIn.addActionListener(listener);
    }
    
   public void addAdminLoginListener(ActionListener listener){
//       System.out.println("Adminlogin");
       AdminLogin.addActionListener(listener);
   }
   
   public  void addELoginListener(ActionListener listener){
       
   }
   public void addForgotListener(ActionListener listener){
//       System.out.println("ForgotPassword");
       btnforgot.addActionListener(listener);
   } 
  
    public javax.swing.JTextField getEmailField() {
        return jTextEmail;
    }
 
    public javax.swing.JTextField getPasswordField() {
        return jTextPass;
    } 
}
