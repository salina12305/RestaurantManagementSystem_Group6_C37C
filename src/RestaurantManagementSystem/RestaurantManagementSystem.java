/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RestaurantManagementSystem ;

import Controller.OrderController;
import Database.*;
import View.OrderFrame;

/**
 *
 * @author uttu
 */
public class RestaurantManagementSystem {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        Database db= new MySqlConnection();
//        if(db.openConnection()!=null){
//            System.out.println("Database conneected successfully!");
//        }else{
//            System.out.println("Failed to connect to database");
//        } 
//    }   
//}

    public static void main(String[] args) {
        
           OrderFrame orderFrame = new OrderFrame();
OrderController orderController = new OrderController(orderFrame);
orderFrame.setVisible(true);

    }
}

