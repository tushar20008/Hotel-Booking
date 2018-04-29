/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.servlet;

import hotel.utils.MyUtils;
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Home", urlPatterns = {"/home/*"})
public class Home extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public Home() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
        String role = request.getParameter("user");
        RequestDispatcher dispatcher = null;
       // Forward to /WEB-INF/views/homeView.jsp
       
        if(role != null){
            
            String guest  = request.getParameter("guest");
            if("1".equals(guest)){
                Cookie guestC = new Cookie("guest", guest);
                response.addCookie(guestC);
                MyUtils.deleteUserCookie(response);
            }
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/"+role+"/home.jsp");         
        }
        else{
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
        }
        dispatcher.forward(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}