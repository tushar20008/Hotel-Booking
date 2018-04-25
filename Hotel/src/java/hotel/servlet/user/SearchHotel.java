/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.user;

import hotel.beans.Account;
import hotel.beans.Booking;
import hotel.beans.HotelInfo;
import static hotel.conn.SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC;
import hotel.utils.CommonUtils;
import hotel.utils.CustomerDBUtils;
import hotel.utils.ManagerDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tushar
 */
@WebServlet(name = "SearchHotel", urlPatterns = {"/searchHotel", "/book"})
public class SearchHotel extends HttpServlet {

    public SearchHotel() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getRequestURL().toString();
        RequestDispatcher dispatcher = null;
        if(url.contains("/book")){
            String hotelId = request.getParameter("hotelId");
            String startDate = request.getParameter("startdate");
            String endDate = request.getParameter("enddate");
            int singleRooms = Integer.parseInt(request.getParameter("singleRooms"));
            int doubleRooms = Integer.parseInt(request.getParameter("doubleRooms"));
            String bookId = "test";
            
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
            
            String username = MyUtils.getUserNameInCookie(request);
            for(){
                Booking booking = new Booking();
                booking.setBookingId(bookId);
                booking.setSingleRoom(singleRooms);
                booking.setDoubleRoom(doubleRooms);
                booking.setHotelId(hotelId);
                booking.setCost((singleRooms * hotel.getSingleRoomPrice()) + (doubleRooms * hotel.getDoubleRoomPrice()) - hotel.getDiscount());
                booking.setUsername(username);
                booking.setDate();
                try {
                    CustomerDBUtils.bookHotel(conn, booking);
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }
            
            response.sendRedirect("/checkout");
        }
        else{
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/customer/searchHotel.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("location");
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        int singleRooms = Integer.parseInt(request.getParameter("singleRooms"));
        int doubleRooms = Integer.parseInt(request.getParameter("doubleRooms"));

        boolean hasError;
        String errorString = "";
        List<HotelInfo> availableHotels = new ArrayList<HotelInfo>();
        
        
        if (location == null || startDate == null || endDate == null) {
            hasError = true;
            errorString = "Search parameters are wrong";
        } else {
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                
                List<HotelInfo> hotelList = null;
                hotelList = CustomerDBUtils.queryHotelsByLocation(conn, location);
                
                for (HotelInfo h : hotelList) {
                    
                    Boolean canAdd = CustomerDBUtils.queryAvailabilityOfHotelByDates(conn, startDate, endDate, singleRooms, doubleRooms, h);
                    if(canAdd)
                        availableHotels.add(h);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Store info in request attribute, before forward to views
            request.setAttribute("errorString", errorString);
            request.setAttribute("availableHotelsList", availableHotels);

            // Forward to /WEB-INF/views/productListView.jsp
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/customer/searchHotel.jsp");
            dispatcher.forward(request, response);
    }
}
