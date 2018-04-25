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
    
    private String userID;
    private String roomID;
    private String hotelID;
    private String CityID;
    private int BookingID;
    
    public Booking(String HotelID, String RoomID, String UserID,String CityID) {
       
            setHotelID(HotelID);
            setRoomID(RoomID);
            setUserID(UserID);
            setCityID(CityID);
         }

   

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public void setCityID(String CityID) {
       
        this.CityID = CityID;
    }
    
    public String getCityID()
            
    {
       return this.CityID; 
    }

    public void setBookingID(int bookingID) {
        
        this.BookingID=bookingID;
       }
    
    public int getBookingID ()
            
    {
        return this.BookingID;
    }
    
}
