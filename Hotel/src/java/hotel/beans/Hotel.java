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
    private String city;
    private int price, numberOfSingle, numberOfDouble, discount;
    
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
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumberOfSingle(int nSingle) {
        this.numberOfSingle = nSingle;
    }
    
     public int getNumberOfSingle() {
        return numberOfSingle;
    }

    public void setNumberOfDouble(int nDouble) {
        this.numberOfDouble = nDouble;
    }
    
     public int getNumberOfDouble() {
        return numberOfDouble;
    }
     
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
     public int getDiscount() {
        return discount;
    }
}