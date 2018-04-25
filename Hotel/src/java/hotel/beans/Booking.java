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
public class Booking {
    
    private String date, username, hotelId, bookingId;
    private int nSingleRoom, nDoubleRoom, cost;
    
    public void setBookingId(String id){
        this.bookingId = id;
    }
    
    public String getBookingId(){
        return bookingId;
    }
    
    public void setHotelId(String id){
        this.hotelId = id;
    }
    
    public String getHotelId(){
        return hotelId;
    }
       
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setCost(int cost){
        this.cost = cost;
    }
    
    public int getCost(){
        return cost;
    }
    
    public void setSingleRoom(int n){
        this.nSingleRoom = n;
    }
    
    public int getSingleRoom(){
        return nSingleRoom;
    }
    
    public void setDoubleRoom(int n){
        this.nDoubleRoom = n;
    }
        
    public int getDoubleRoom(){
        return nDoubleRoom;
    }
}
