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
import java.time.LocalDate;
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
        String sql = "Select * from hotel where location=?";

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

        String sql = "Select date, SUM(nSingleRoom) as SingleRooms, Sum(nDoubleRoom) as DoubleRooms "
                + "from booking where hotelId=? and date >= ? and date <= ? Group by date";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, startDate);
        pstm.setString(3, endDate);

        ResultSet rs = pstm.executeQuery();

        Boolean canAdd = false;
        while (rs.next()) {
            int sRoomsUsed = rs.getInt("SingleRooms");
            int dRoomsUsed = rs.getInt("DoubleRooms");

            if (singleRoomCap - sRoomsUsed >= sRooms && doubleRoomCap - dRoomsUsed >= dRooms) {
                canAdd = true;
            }
        }
        System.out.println(canAdd);
        return canAdd;
    }

    public static void bookHotel(Connection conn, Booking booking) throws SQLException {
        String sql = "Insert into booking(date, nSingleRoom, nDoubleRoom, username, hotelId, cost, bookingId) values (?,?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, booking.getDate());
        pstm.setInt(2, booking.getnSingleRoom());
        pstm.setInt(3, booking.getnDoubleRoom());
        pstm.setString(4, booking.getUsername());
        pstm.setString(5, booking.getHotelId());
        pstm.setInt(6, booking.getCost());
        pstm.setString(7, booking.getBookingId());

        pstm.executeUpdate();
    }

    public static List<HotelInfo> queryGetRecoms(Connection conn, String username) throws SQLException {
        String sql = "Select * from recommendation where username=?";

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
            String sql = "Select * from hotel where hotelId=?";

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

    public static List<Booking> queryGetBookings(Connection conn, String username) throws SQLException {
        String sql = "Select bookingId, hotelId, nSingleRoom, nDoubleRoom, Cost "
                + "from booking where username=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);

        ResultSet rs = pstm.executeQuery();
        List<Booking> list = new ArrayList<>();

        String prevBook = " ";
        while (rs.next()) {

            String bookingId = rs.getString("bookingId");

            if (!prevBook.equals(bookingId)) {

                String id = rs.getString("hotelId");
                int nSingleRoom = rs.getInt("nSingleRoom");
                int nDoubleRoom = rs.getInt("nDoubleRoom");
                int cost = rs.getInt("cost");

                Booking book = new Booking();
                book.setHotelId(id);
                book.setBookingId(bookingId);
                book.setnSingleRoom(nSingleRoom);
                book.setnDoubleRoom(nDoubleRoom);
                book.setCost(cost);

                list.add(book);
                prevBook = bookingId;
            }
        }
        return list;
    }

    public static void deleteBooking(Connection conn, String code) throws SQLException {
        String sql = "Delete From booking where bookingId= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();
    }

    public static Booking findBooking(Connection conn, String code) throws SQLException {
        String sql = "Select * from booking a where a.bookingId=?";

        PreparedStatement pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String id = rs.getString("hotelId");
            String bookingId = rs.getString("bookingId");
            String username = rs.getString("username");
            int nSingleRoom = rs.getInt("nSingleRoom");
            int nDoubleRoom = rs.getInt("nDoubleRoom");
            int cost = rs.getInt("cost");

            String date = rs.getString("date").split(" ")[0] + ",";
            rs.last();
            date += rs.getString("date").split(" ")[0];

            System.out.println(date);

            Booking book = new Booking();
            book.setHotelId(id);
            book.setUsername(username);
            book.setBookingId(bookingId);
            book.setnSingleRoom(nSingleRoom);
            book.setnDoubleRoom(nDoubleRoom);
            book.setCost(cost);
            book.setDate(date);
            return book;
        }
        return null;
    }

    public static void updateBooking(Connection conn, Booking book) throws SQLException {
        
        Booking oldBook = findBooking(conn, book.getBookingId());
        deleteBooking(conn, book.getBookingId());
        
        book.setHotelId(oldBook.getHotelId());
        book.setUsername(oldBook.getUsername());
        
        System.out.println(book.getUsername());
        
        HotelInfo hotel = ManagerDBUtils.findHotel(conn, book.getHotelId());
        int singleRoomPrice = hotel.getSingleRoomPrice();
        int doubleRoomPrice = hotel.getDoubleRoomPrice();
        int discount = hotel.getDiscount();
        
        int cost = (singleRoomPrice * book.getnSingleRoom()) + (doubleRoomPrice * book.getnDoubleRoom()) - discount;
        book.setCost(cost);
        
        String [] date = book.getDate().split(",");
        
        LocalDate start = LocalDate.parse(date[0]),
                      end   = LocalDate.parse(date[1]);
        LocalDate next = start.minusDays(1);
            
        while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {

            book.setDate(next.toString());
            CustomerDBUtils.bookHotel(conn, book);

        }
    }

    public static boolean checkNewBooking(Connection conn, Booking booking) throws SQLException {
        String sql = "select * from hotel where hotelId = (select distinct(hotelId) from booking a where a.bookingId=?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, booking.getBookingId());

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            HotelInfo hotelBean = new HotelInfo(rs.getString("hotelId"),
                     rs.getString("name"),
                     rs.getString("location"),
                     rs.getInt("singleRooms"),
                     rs.getInt("singleRoomPrice"),
                     rs.getInt("doubleRooms"),
                     rs.getInt("doubleRoomPrice"));
            System.out.println(hotelBean.getName());
            System.out.println(booking.getDate());
            String[] bookingDates = (booking.getDate()).split(",");
            System.out.println(bookingDates[0]);
            System.out.println(bookingDates[1]);
            return queryAvailabilityOfHotelByDates(conn, bookingDates[0], bookingDates[1], booking.getnSingleRoom(), booking.getnDoubleRoom(), hotelBean);
        }
        return false;
    }
}
