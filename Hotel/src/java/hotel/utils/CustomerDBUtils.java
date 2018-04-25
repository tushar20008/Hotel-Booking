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
import java.util.List;

/**
 *
 * @author Aditya
 */
public class CustomerDBUtils {

    public static List<Hotel> queryHotelsByLocation(Connection conn, String location) throws SQLException {
        String sql = "Select * from dbo.hotel where location LIKE \"" + location + "\"";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        List<Hotel> list = new ArrayList<Hotel>();

        while (rs.next()) {
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

}
