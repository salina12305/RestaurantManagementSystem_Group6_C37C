/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author uttu
 */
public class User {
    private int id;
    private String email;
    private String password;
   
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
  
    public String getEmail(){
        return email;
    }
        public void setEmail(String email){
        this.email=email;
    }
            public String getPassword(){
        return password;
    }
        public void setPassword(String password){
        this.password=password;
    }
        public int getId(){
        return id;
    }
        public void setId(Integer id){
        this.id= id;
        }
}
