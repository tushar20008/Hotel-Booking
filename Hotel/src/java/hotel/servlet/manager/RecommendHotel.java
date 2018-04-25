



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.manager;

import hotel.beans.Account;
import hotel.beans.HotelInfo;
import hotel.beans.Recommendation;
import hotel.utils.AdminDBUtils;
import hotel.utils.ManagerDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "RecommendHotel", urlPatterns = {"/recommendHotel"})
public class RecommendHotel extends HttpServlet {

    public RecommendHotel() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        List<HotelInfo> list = null;
        try {
            list = ManagerDBUtils.queryGetHotels(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("hotelList", list);
        
        String code = (String) request.getParameter("code");
 
        Account member = null; 
        try {
            member = ManagerDBUtils.findMember(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        if (errorString != null && member == null) {
            response.sendRedirect(request.getServletPath() + "/hotelList");
            return;
        }
        request.setAttribute("member", member);
        
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/manager/recommendHotel.jsp");
        dispatcher.forward(request, response);
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userId = (String) request.getParameter("code");
        String hotelId = (String) request.getParameter("hotel");
        
        Recommendation booking = new Recommendation(); 
        booking.setUserID(userId);
        booking.setHotelID(hotelId);
        
        String errorString = null;
        
        try {
            ManagerDBUtils.insertRecommendation(conn, booking);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/manager/recommendHotel.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/managerList");
        }
    }
 
}