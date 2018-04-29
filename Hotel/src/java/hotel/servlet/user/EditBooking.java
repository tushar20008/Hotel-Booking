/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.user;

import hotel.beans.Account;
import hotel.beans.Booking;
import hotel.utils.AdminDBUtils;
import hotel.utils.CustomerDBUtils;
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
@WebServlet(name = "EditBooking", urlPatterns = {"/editBooking"})
public class EditBooking extends HttpServlet {

    public EditBooking() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Booking booking = null;
 
        String errorString = null;
 
        try {
            booking = CustomerDBUtils.findBooking(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && booking == null) {
            response.sendRedirect(request.getServletPath() + "/bookingList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("booking", booking);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/customer/editBooking.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int nSingleRoom = Integer.parseInt(request.getParameter("nSingleRoom"));
        int nDoubleRoom = Integer.parseInt(request.getParameter("nDoubleRoom"));
        String code = (String) request.getParameter("code");
        String []dates = request.getParameterValues("date");
        Booking booking = new Booking();
        booking.setBookingId(code);
        booking.setnSingleRoom(nSingleRoom);
        booking.setnDoubleRoom(nDoubleRoom);
        booking.setDate(dates[0]+","+dates[1]);
        
        String errorString = null;
 
        try {
            CustomerDBUtils.updateBooking(conn, booking);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("booking", booking);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/customer/editBooking.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/bookingList");
        }
    }
}
