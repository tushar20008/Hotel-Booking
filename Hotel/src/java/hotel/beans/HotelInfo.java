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

public class HotelInfo {
    
    private String name;
    private String id;
    private String location;
    private int singleRoomCap;
    private int singleRoomPrice;
    private int doubleRoomCap;
    private int doubleRoomPrice;
    private int discount;

    public HotelInfo(String id, String name, String lcn, int singleRoomCap, int singleRoomPrice, int doubleRoomCap, int doubleRoomPrice) {
        this.id = id;
        this.name = name;
        this.location = lcn;
        this.singleRoomCap = singleRoomCap;
        this.doubleRoomCap = doubleRoomCap;
        this.singleRoomPrice = singleRoomPrice;
        this.doubleRoomPrice = doubleRoomPrice;
        this.discount = 0;
    }

    public HotelInfo() {
    }
    
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

    public int getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(int singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public int getDoubleRoomCap() {
        return doubleRoomCap;
    }

    public void setDoubleRoomCap(int doubleRoomCap) {
        this.doubleRoomCap = doubleRoomCap;
    }

    public int getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(int doubleRoomPrice) {
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
}
