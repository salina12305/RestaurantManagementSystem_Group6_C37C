
package RestaurantManagementSystem ;
import Controller.ESignInController;

import View.EmployeeSignIn;
import Database.*;

public class RestaurantManagementSystem {
    public static void main(String[] args) {

      EmployeeSignIn esignInForm = new EmployeeSignIn();
     ESignInController esignInController = new ESignInController(esignInForm);
     esignInController.open(); 
    } 
}



