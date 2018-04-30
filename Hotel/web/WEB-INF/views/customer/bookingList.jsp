<%-- 
    Document   : memberList
    Created on : 25 Apr, 2018, 6:59:33 PM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel List</title>
    </head>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Hotel Bookings</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Booking Id</th>
          <th>Hotel Id</th>
          <th>Single Rooms Booked</th>
          <th>Double Rooms Booked</th>
          <th>Total Cost</th>
          <th>Modify</th>
          <th>Cancel</th>
       </tr>
       <c:forEach items="${bookingList}" var="booking" >
          <tr>
             <td>${booking.bookingId}</td>
             <td>${booking.hotelId}</td>
             <td>${booking.nSingleRoom}</td>
             <td>${booking.nDoubleRoom}</td>
             <td>${booking.cost}</td>
             <td>
                <a href="editBooking?code=${booking.bookingId}">Edit</a>
             </td>
             <td>
                <a href="deleteBooking?code=${booking.bookingId}">Cancel</a>
             </td>
          </tr>
       </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/home?user=cutomer">Go Back</a>
    </body>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
</html>
