/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.user;

import hotel.beans.Hotel;
import hotel.utils.CommonUtils;
import hotel.utils.CustomerDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "SearchHotel", urlPatterns = {"/searchHotel"})
public class SearchHotel extends HttpServlet {
       public SearchHotel() {
       super();
   }
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/customer/searchHotel.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("location");
        String start = request.getParameter("startdate");
        String end = request.getParameter("enddate");

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = ft.parse(start);
             endDate = ft.parse(end);
        } catch (ParseException ex) {
            Logger.getLogger(SearchHotel.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        boolean hasError;
        String errorString;
        
        if (location == null || startDate == null || endDate == null || startDate.before(endDate)){
            hasError = true;
            errorString = "Search parameters are wrong";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            
            List<Hotel> hotelList = null;
            hotelList = CustomerDBUtils.queryHotelsByLocation(conn, location);
            
            List<Hotel> availableHotels = null;
            for(Hotel h: hotelList){
                
            }
            
//            try {
//                // Find the user in the DB.
//                user = CommonUtils.findUser(conn, userName, password);
//
//                if (user == null) {
//                    hasError = true;
//                    errorString = "User Name or password invalid";
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                hasError = true;
//                errorString = e.getMessage();
//            }
        }
    }
}
