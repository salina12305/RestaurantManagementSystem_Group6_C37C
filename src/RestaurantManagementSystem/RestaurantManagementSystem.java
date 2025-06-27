
package RestaurantManagementSystem ;

import Controller.ESignInController;
import View.EmployeeSignIn;
import Database.*;

public class RestaurantManagementSystem {
    public static void main(String[] args) {
//          System.out.println("Hello, world!"); 
//    }

//      EmployeeSignIn esignInForm = new EmployeeSignIn();
//     ESignInController esignInController = new ESignInController(esignInForm);
//     esignInController.open(); 
//    } 
EmployeeSignIn esignInForm = new EmployeeSignIn();
ESignInController esignInController = new ESignInController(esignInForm);
esignInController.open();
    }
//        // TODO code application logic here
//        Database db= new MySqlConnection();
//        if(db.openConnection()!=null){
//            System.out.println("Database conneected successfully!");
//        }else{
//            System.out.println("Failed to connect to database");
//        } 
////        ManageMenu view=new ManageMenu();
////        MenuController 
//    }   
}



