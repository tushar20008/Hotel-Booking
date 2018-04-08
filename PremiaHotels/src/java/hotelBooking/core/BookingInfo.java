/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelBooking.core;
/**
 *
 * @author Aditya
 */
import java.util.UUID;

public class BookingInfo {

    public BookingInfo(String bookingID, UserInfo user, HotelInfo hotel, String startDate, String endDate, int reqSingleRoom, int reqDoubleRoom, float costOfBooking) {
        
        this.bookingID = UUID.randomUUID().toString();
        this.user = user;
        this.hotel = hotel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reqSingleRoom = reqSingleRoom;
        this.reqDoubleRoom = reqDoubleRoom;
        this.costOfBooking = costOfBooking; //need to think about how to calculate
                                            //cost of booking
    }
    private String bookingID;
    private UserInfo user;
    private HotelInfo hotel;
    private String startDate;
    private String endDate;
    private int reqSingleRoom;
    private int reqDoubleRoom;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public HotelInfo getHotel() {
        return hotel;
    }

    public void setHotel(HotelInfo hotel) {
        this.hotel = hotel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getReqSingleRoom() {
        return reqSingleRoom;
    }

    public void setReqSingleRoom(int reqSingleRoom) {
        this.reqSingleRoom = reqSingleRoom;
    }

    public int getReqDoubleRoom() {
        return reqDoubleRoom;
    }

    public void setReqDoubleRoom(int reqDoubleRoom) {
        this.reqDoubleRoom = reqDoubleRoom;
    }

    public float getCostOfBooking() {
        return costOfBooking;
    }

    public void setCostOfBooking(float costOfBooking) {
        this.costOfBooking = costOfBooking;
    }
    private float costOfBooking;
}
