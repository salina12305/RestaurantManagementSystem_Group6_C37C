/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Signup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author aiden
 */
public class Menu {
    public static void handleLogout(JFrame currentFrame) {
        int confirm = JOptionPane.showConfirmDialog(currentFrame, 
            "Are you sure you want to logout?", 
            "Logout", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            currentFrame.setVisible(false);
            new Signup().setVisible(true);
        }
    }
    
}


