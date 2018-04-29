/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.user;

import hotel.beans.Booking;
import hotel.beans.HotelInfo;
import hotel.utils.CustomerDBUtils;
import hotel.utils.ManagerDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tushar
 */
@WebServlet(name = "Checkout", urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {
    public Checkout() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        Cookie[] cookies = request.getCookies();
        String location = null, startDate = null, endDate = null;
        int singleRooms = -1,doubleRooms = -1; 
        
        for(int i = 0; i < cookies.length; i++) { 
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals("location")) {
                location = cookie1.getValue();
            }
            if (cookie1.getName().equals("startDate")) {
                startDate = cookie1.getValue();
            }
            if (cookie1.getName().equals("endDate")) {
                endDate = cookie1.getValue();
            }
            if (cookie1.getName().equals("singleRooms")) {
                singleRooms = Integer.parseInt(cookie1.getValue());
            }
            if (cookie1.getName().equals("doubleRooms")) {
                doubleRooms = Integer.parseInt(cookie1.getValue());
            }
        }  
        
        if(location != null && startDate != null && endDate != null && singleRooms != -1 && doubleRooms != -1){
            
            String hotelId = request.getParameter("hotelId");
            String username = MyUtils.getUserNameInCookie(request);
            String alphabet= "abcdefghijklmnopqrstuvwxyz";
            String bookId = "";
            Random random = new Random();
            int randomLen = 1+random.nextInt(9);
            for (int i = 0; i < randomLen; i++) {
                char c = alphabet.charAt(random.nextInt(26));
                bookId+=c;
            }
            
            Connection conn = MyUtils.getStoredConnection(request);
            HotelInfo hotel = null;
 
            String errorString = null;
 
            try {
                hotel = ManagerDBUtils.findHotel(conn, hotelId);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
 
            if (errorString != null && hotel == null) {
                response.sendRedirect(request.getServletPath() + "/searchHotel");
                return;
            }
            
            LocalDate start = LocalDate.parse(startDate),
                      end   = LocalDate.parse(endDate);
            LocalDate next = start.minusDays(1);
            
            Booking booking = null;
            while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
       
                booking = new Booking();
                booking.setBookingId(bookId);
                booking.setSingleRoom(singleRooms);
                booking.setDoubleRoom(doubleRooms);
                booking.setHotelId(hotelId);
                // Give Discount only if user
                booking.setCost((singleRooms * hotel.getSingleRoomPrice()) + (doubleRooms * hotel.getDoubleRoomPrice()) - hotel.getDiscount());
                booking.setDiscount(hotel.getDiscount());
                booking.setUsername(username);
                booking.setDate(next.toString());
                try {
                    CustomerDBUtils.bookHotel(conn, booking);
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }
            
            request.setAttribute("firstDay", start.toString());
            request.setAttribute("bookingInfo", booking);
            
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/customer/checkout.jsp");
            dispatcher.forward(request, response);
        }
    }
}