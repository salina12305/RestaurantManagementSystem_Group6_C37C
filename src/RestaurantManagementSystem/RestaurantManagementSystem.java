
package RestaurantManagementSystem ;
import Database.*;

public class RestaurantManagementSystem {

    public static void main(String[] args) {
        // TODO code application logic here
        Database db= new MySqlConnection();
        if(db.openConnection()!=null){
            System.out.println("Database conneected successfully!");
        }else{
            System.out.println("Failed to connect to database");
        } 
//        ManageMenu view=new ManageMenu();
//        MenuController 
    }   
}



