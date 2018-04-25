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
            String code = rs.getString("id");
            String name = rs.getString("name");
            String location = rs.getString("location");
            HotelInfo hotel = new HotelInfo();
            hotel.setId(code);
            hotel.setCity(location);
            hotel.setName(name);
            list.add(hotel);
        }
        return list;
    }
    
        public static void insertRecommendation(Connection conn, Recommendation recom) throws SQLException {
        String sql = "Insert into recommendation(uId, hotelId) values (?,?)";
 
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
            String name = rs.getString("username");
            String location = rs.getString("password");
            int discount = rs.getInt("discount");
            int singleRoomCap = rs.getInt("singleRooms");
            int doubleRoomCap = rs.getInt("doubleRooms");
            int singleRoomPrice = rs.getInt("singleRoomPrice");
            int doubleRoomPrice = rs.getInt("doubleRoomPrice");
            
            HotelInfo hotel = new HotelInfo();
            hotel.setName(name);
            hotel.setLocation(location);
            hotel.setDiscount(discount);
            hotel.setSingleRoomCap(singleRoomCap);
            hotel.setDoubleRoomCap(doubleRoomCap);
            hotel.setSingleRoomPrice(singleRoomPrice);
            hotel.setDoubleRoomPrice(doubleRoomPrice);
            hotel.setId(code);
            return hotel;
        }
        return null;
    }
}
