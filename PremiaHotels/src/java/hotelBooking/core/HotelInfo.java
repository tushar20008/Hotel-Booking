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
public class HotelInfo {

    public HotelInfo(String name, String location, String description, int singleRoomCap, int doubleRoomCap, float singleRoomPrice, float doubleRoomPrice, int rating) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.singleRoomCap = singleRoomCap;
        this.doubleRoomCap = doubleRoomCap;
        this.singleRoomPrice = singleRoomPrice;
        this.doubleRoomPrice = doubleRoomPrice;
        this.rating = rating;
        this.hotelId = this.name+this.location;
    }
    private String hotelId;
    private String name;
    private String location;
    private String description;
    private int singleRoomCap;
    private int doubleRoomCap;
    private float singleRoomPrice;
    private float doubleRoomPrice;
    private int rating;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSingleRoomCap() {
        return singleRoomCap;
    }

    public void setSingleRoomCap(int singleRoomCap) {
        this.singleRoomCap = singleRoomCap;
    }

    public int getDoubleRoomCap() {
        return doubleRoomCap;
    }

    public void setDoubleRoomCap(int doubleRoomCap) {
        this.doubleRoomCap = doubleRoomCap;
    }

    public float getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(float singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public float getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(float doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
