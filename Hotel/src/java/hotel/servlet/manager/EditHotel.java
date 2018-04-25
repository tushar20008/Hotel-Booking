/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.manager;

import hotel.beans.Account;
import hotel.beans.HotelInfo;
import hotel.utils.AdminDBUtils;
import hotel.utils.ManagerDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "EditHotel", urlPatterns = {"/editHotel"})
public class EditHotel extends HttpServlet {


    public EditHotel() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        HotelInfo hotel = null;
 
        String errorString = null;
 
        try {
            hotel = ManagerDBUtils.findHotel(conn, code);
            System.out.println(hotel.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        if (errorString != null && hotel == null) {
            response.sendRedirect(request.getServletPath() + "/hotelList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("hotel", hotel);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/manager/editHotel.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String name = (String) request.getParameter("name");
        String location = (String) request.getParameter("location");
        int discount = Integer.parseInt(request.getParameter("discount"));
        int singleRoomCap = Integer.parseInt(request.getParameter("singleRoomCap"));
        int doubleRoomCap = Integer.parseInt(request.getParameter("doubleRoomCap"));
        int singleRoomPrice = Integer.parseInt(request.getParameter("singleRoomPrice"));
        int doubleRoomPrice = Integer.parseInt(request.getParameter("doubleRoomPrice"));
        String code = (String) request.getParameter("code");
        
        HotelInfo hotel = new HotelInfo(code, name, location, singleRoomCap, singleRoomPrice, doubleRoomCap, doubleRoomPrice);
        hotel.setDiscount(discount);        
        
        String errorString = null;
 
        ManagerDBUtils.updateHotel(conn, hotel);
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("hotel", hotel);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/manager/editHotel.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/hotelList");
        }
    }
 
}
