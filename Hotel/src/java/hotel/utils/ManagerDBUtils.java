/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.utils;

import hotel.beans.Account;
import hotel.beans.HotelInfo;
import hotel.beans.Recommendation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aditya
 */
public class ManagerDBUtils {
    
    public static List<Account> queryGetMembers(Connection conn) throws SQLException {
        String sql = "Select * from users where role='customer'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<Account>();
        while (rs.next()) {
            String code = rs.getString("id");
            String name = rs.getString("username");
            String password = rs.getString("password");
            Account member = new Account(name, password);
            member.setCode(code);
            list.add(member);
        }
        return list;
    }
    
    public static List<HotelInfo> queryGetHotels(Connection conn) throws SQLException {
        String sql = "Select * from hotel ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<HotelInfo> list = new ArrayList<HotelInfo>();
        while (rs.next()) {
            String code = rs.getString("hotelId");
            String name = rs.getString("name");
            String location = rs.getString("location");
            HotelInfo hotel = new HotelInfo();
            hotel.setId(code);
            hotel.setLocation(location);
            hotel.setName(name);
            list.add(hotel);
        }
        return list;
    }
    
        public static void insertRecommendation(Connection conn, Recommendation recom) throws SQLException {
        String sql = "Insert into recommendation(username, hotelId) values (?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, recom.getUserID());
        pstm.setString(2, recom.getHotelID());
 
        pstm.executeUpdate();
    }
        
    public static Account findMember(Connection conn, String code) throws SQLException {
        String sql = "Select * from users a where a.id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            
            Account member = new Account(username, password);
            member.setCode(code);
            return member;
        }
        return null;
    }
    
    public static HotelInfo findHotel(Connection conn, String code) throws SQLException {
        String sql = "Select * from hotel a where a.hotelId=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String name = rs.getString("name");
            String location = rs.getString("location");
            int discount = rs.getInt("discount");
            int singleRoomCap = rs.getInt("singleRooms");
            int doubleRoomCap = rs.getInt("doubleRooms");
            int singleRoomPrice = rs.getInt("singleRoomPrice");
            int doubleRoomPrice = rs.getInt("doubleRoomPrice");
            
            HotelInfo hotel = new HotelInfo(code, name, location, singleRoomCap, singleRoomPrice, doubleRoomCap, doubleRoomPrice);
            hotel.setDiscount(discount);
            return hotel;
        }
        return null;
    }

    public static void updateHotel(Connection conn, HotelInfo hotel) {
        try {
            String sql = "Update hotel set location=?, singleRooms=?, doubleRooms=?, singleRoomPrice=?, doubleRoomPrice=?, name=? where hotelId=?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, hotel.getLocation());
            pstm.setInt(2, hotel.getSingleRoomCap());
            pstm.setInt(3, hotel.getDoubleRoomCap());
            pstm.setInt(4, hotel.getSingleRoomPrice());
            pstm.setInt(5, hotel.getDoubleRoomPrice());
            pstm.setString(6, hotel.getName());
            pstm.setString(7, hotel.getId());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
