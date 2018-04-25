/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.beans;

/**
 *
 * @author Tushar
 */
public class Account {
    
   private String userName, role;
   private String password;
    
   public Account(String name, String password) {
        this.userName = name;
        this.password = password;
   }
    
   public Account(String name, String password, String role) {
        this.userName = name;
        this.password = password;
        this.role = role;
   }
    
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 
   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }
   
   public String getRole() {
       return role;
   }
 
   public void setRole(String role) {
       this.role = role;
   }
 
}
