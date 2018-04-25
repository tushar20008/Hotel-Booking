/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.utils;

import hotel.beans.Account;
import hotel.beans.Hotel;
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

    public static List<Hotel> queryHotelsByLocation(Connection conn, String location) throws SQLException {
        String sql = "Select * from dbo.hotel where location LIKE '%" + location + "%'";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        List<Hotel> list = new ArrayList<Hotel>();

        while (rs.next()) {
            System.out.println("timepass");
            
            String id = rs.getString("hotelId");
            String name = rs.getString("name");
            String lcn = rs.getString("location");
            int singleRoomCap = rs.getInt("singleRooms");
            float singleRoomPrice = rs.getInt("singleRoomPrice");
            int doubleRoomCap = rs.getInt("doubleRooms");
            float doubleRoomPrice = rs.getInt("doubleRoomPrice");

            Hotel hotel = new Hotel();
            hotel.setDoubleRoomCap(doubleRoomCap);
            hotel.setDoubleRoomPrice(doubleRoomPrice);
            hotel.setId(id);
            hotel.setLocation(lcn);
            hotel.setName(name);
            hotel.setSingleRoomCap(singleRoomCap);
            hotel.setSingleRoomPrice(singleRoomPrice);

            list.add(hotel);
        }
        return list;
    }

    public static Boolean queryAvailabilityOfHotelByDates(Connection conn, Date startDate, Date endDate, int sRooms, int dRooms, Hotel h) throws SQLException {
        String id = h.getId();
        String name = h.getName();
        String lcn = h.getLocation();
        int singleRoomCap = h.getSingleRoomCap();
        int doubleRoomCap = h.getDoubleRoomCap();
        
        String sql ="Select date, SUM(nSingleRoom) as SingleRooms, Sum(nDoubleRoom) as DoubleRooms " +
                    "from booking " +
                    "where hotelId =? " +
                    "and date >= ? "+
                    "and date <= ? "+
                    "group by date";
//                    "and startDate <="+startDate+  //27/04/2018
//                    "and endDate >="+endDate; // 30/04/2018

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2,startDate.toString());
        pstm.setString(3,endDate.toString());

        ResultSet rs = pstm.executeQuery();
        
        Boolean canAdd = false;
        while(rs.next()){
            System.out.println("timepass 2");
            int sRoomsUsed = rs.getInt("SingleRooms");
            int dRoomsUsed = rs.getInt("DoubleRooms");
            
            if(singleRoomCap - sRoomsUsed > sRooms && doubleRoomCap - dRoomsUsed > dRooms)
                canAdd = true;
        }
        System.out.println(canAdd);
        return canAdd;
    }

}
