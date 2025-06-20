
package RestaurantManagementSystem ;
import Controller.ESignInController;

import View.EmployeeSignIn;


import Database.*;
import View.Bill;
import controller.BillController;


public class RestaurantManagementSystem {

    public static void main(String[] args) {


         EmployeeSignIn esignInForm = new EmployeeSignIn();                 
        ESignInController esignInController = new ESignInController(esignInForm);  
        esignInController.open(); 
       
 
    } 
}



