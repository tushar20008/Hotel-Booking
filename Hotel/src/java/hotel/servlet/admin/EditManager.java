/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet.admin;

import hotel.beans.Account;
import hotel.utils.AdminDBUtils;
import hotel.utils.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditManager", urlPatterns = {"/editManager"})
public class EditManager extends HttpServlet {

    private static final long serialVersionUID = 1L;
 
    public EditManager() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Account manager = null;
 
        String errorString = null;
 
        try {
            manager = AdminDBUtils.findManager(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && manager == null) {
            response.sendRedirect(request.getServletPath() + "/managerList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", manager);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/editManager.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String code = (String) request.getParameter("code");
        
        Account manager = new Account(userName, password);
        manager.setCode(code);
        
        String errorString = null;
 
        try {
            AdminDBUtils.updateManager(conn, manager);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", manager);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/admin/editManager.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/managerList");
        }
    }
 
}