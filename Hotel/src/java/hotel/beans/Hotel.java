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

public class Hotel {
    
    private String name;
    private String id;
    private String location;
    private int singleRoomCap;
    private float singleRoomPrice;
    private int doubleRoomCap;
    private float doubleRoomPrice;
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSingleRoomCap() {
        return singleRoomCap;
    }

    public void setSingleRoomCap(int singleRoomCap) {
        this.singleRoomCap = singleRoomCap;
    }

    public float getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(float singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public int getDoubleRoomCap() {
        return doubleRoomCap;
    }

    public void setDoubleRoomCap(int doubleRoomCap) {
        this.doubleRoomCap = doubleRoomCap;
    }

    public float getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(float doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return location;
    }

    public void setCity(String city) {
        this.location = city;
    }    
}
