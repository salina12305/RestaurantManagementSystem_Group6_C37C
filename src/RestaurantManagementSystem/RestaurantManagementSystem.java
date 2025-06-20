/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RestaurantManagementSystem ;

import Database.*;
import View.Bill;
import controller.BillController;

/**
 *
 * @author uttu
 */
public class RestaurantManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Database db= new MySqlConnection();
//        if(db.openConnection()!=null){
//            System.out.println("Database conneected successfully!");
//        }else{
//            System.out.println("Failed to connect to database");
//        } 
//    }

 java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            Bill billView = new Bill();
            BillController controller = new BillController(billView); // <<== this attaches listener
            controller.open();
        }
    });
}
}

