/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.utils;

import hotel.beans.Account;
import hotel.beans.Booking;
import hotel.beans.HotelInfo;
import hotel.beans.Recommendation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aditya
 */
public class CustomerDBUtils {
    
    public static void insertCustomer(Connection conn, Account customer) throws SQLException {
        String sql = "Insert into users(username, password, role) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, customer.getUserName());
        pstm.setString(2, customer.getPassword());
        pstm.setString(3, customer.getRole());
 
        pstm.executeUpdate();
    }

    public static List<HotelInfo> queryHotelsByLocation(Connection conn, String location) throws SQLException {
        String sql = "Select * from hotel where location=?" ;

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, location);

        ResultSet rs = pstm.executeQuery();

        List<HotelInfo> list = new ArrayList<>();
        
        while (rs.next()) {
            
            String id = rs.getString("hotelId");
            String name = rs.getString("name");
            String lcn = rs.getString("location");
            int singleRoomCap = rs.getInt("singleRooms");
            int singleRoomPrice = rs.getInt("singleRoomPrice");
            int doubleRoomCap = rs.getInt("doubleRooms");
            int doubleRoomPrice = rs.getInt("doubleRoomPrice");
            
            HotelInfo hotel = new HotelInfo(id, name, lcn, singleRoomCap, singleRoomPrice, doubleRoomCap, doubleRoomPrice);
           
            list.add(hotel);
        }
      
        return list;
    }

    public static Boolean queryAvailabilityOfHotelByDates(Connection conn, String startDate, String endDate, int sRooms, int dRooms, HotelInfo h) throws SQLException {
        System.out.println("Running");
        String id = h.getId();
        String name = h.getName();
        String lcn = h.getLocation();
        int singleRoomCap = h.getSingleRoomCap();
        int doubleRoomCap = h.getDoubleRoomCap();
        
        String sql ="Select date, SUM(nSingleRoom) as SingleRooms, Sum(nDoubleRoom) as DoubleRooms " +
                    "from booking where hotelId=? and date >= ? and date <= ? Group by date";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2,startDate);
        pstm.setString(3,endDate);

        ResultSet rs = pstm.executeQuery();
        
        Boolean canAdd = false;
        while(rs.next()){
            int sRoomsUsed = rs.getInt("SingleRooms");
            int dRoomsUsed = rs.getInt("DoubleRooms");
            
            if(singleRoomCap - sRoomsUsed >= sRooms && doubleRoomCap - dRoomsUsed >= dRooms)
                canAdd = true;
        }
        System.out.println(canAdd);
        return canAdd;
    }
    
        public static void bookHotel(Connection conn, Booking booking) throws SQLException {
        String sql = "Insert into booking(date, nSingleRoom, nDoubleRoom, username, password, hotelId, cost, bookingId) values (?,?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, booking.getDate());
        pstm.setInt(2, booking.getSingleRoom());
        pstm.setInt(3, booking.getDoubleRoom());
        pstm.setString(4, booking.getUsername());
        pstm.setString(5, booking.getHotelId());
        pstm.setInt(6, booking.getCost());
        pstm.setString(7, booking.getBookingId());
 
        pstm.executeUpdate();
    }
        
    public static List<HotelInfo> queryGetRecoms(Connection conn, String username) throws SQLException {
        String sql = "Select * from recommendation where username=?" ;

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);

        ResultSet rs = pstm.executeQuery();

        List<HotelInfo> list = new ArrayList<>();
        
        while (rs.next()) {
            
            String id = rs.getString("hotelId");
            HotelInfo hotel = new HotelInfo();
            hotel.setId(id);
            list.add(hotel);
        }
      
        return list;
    }
    
    public static List<HotelInfo> queryGetHotelFromRec(Connection conn, List<HotelInfo> hotels) throws SQLException {
        
        List<HotelInfo> list = new ArrayList<>();
        for (HotelInfo h : hotels) {
            String sql = "Select * from hotel where hotelId=?" ;

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, h.getId());

            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
            
                String id = rs.getString("hotelId");
                String name = rs.getString("name");
                String lcn = rs.getString("location");
                int singleRoomCap = rs.getInt("singleRooms");
                int singleRoomPrice = rs.getInt("singleRoomPrice");
                int doubleRoomCap = rs.getInt("doubleRooms");
                int doubleRoomPrice = rs.getInt("doubleRoomPrice");
            
                HotelInfo hotel = new HotelInfo(id, name, lcn, singleRoomCap, singleRoomPrice, doubleRoomCap, doubleRoomPrice);
           
                list.add(hotel);
            }
        }
        
        return list;
    }
}   
    
