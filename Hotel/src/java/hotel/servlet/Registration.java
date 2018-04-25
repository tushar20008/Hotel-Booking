/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet;

import hotel.beans.Account;
import hotel.utils.CommonUtils;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aditya
 */
@WebServlet(name = "Registration", urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to /WEB-INF/views/registration.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");

        dispatcher.forward(request, response);
    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Account customer = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                customer = new Account(userName, password, "customer");
                CustomerDBUtils.insertCustomer(conn, customer);
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // If error, forward to /WEB-INF/views/registration.jsp
        if (hasError) {
            customer = new Account(userName, password, "customer");

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", customer);

            // Forward to /WEB-INF/views/registration.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");

            dispatcher.forward(request, response);
        } 

        // If no error
        // Store user information in Session
        // And redirect to home page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, customer);

            // If user checked "Remember me".
            //if (remember) {
            MyUtils.storeUserCookie(response, customer);
            //}
            // Else delete cookie.
            //else {
            //    MyUtils.deleteUserCookie(response);
            //}
            if(customer.getRole() != null)
                response.sendRedirect(request.getContextPath() + "/home?user=customer");
        }

    }
}
