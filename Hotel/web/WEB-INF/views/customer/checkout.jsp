<%-- 
    Document   : checkout
    Created on : 25 Apr, 2018, 10:48:41 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Checkout Success</h3>
 
        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>Booking ID : ${bookingInfo.bookingId}</tr><br/>
            <tr>username : ${bookingInfo.username}</tr><br/>
            <tr>hotelId : ${bookingInfo.hotelId}</tr><br/>
            <tr>nSingleRoom : ${booking.nSingleRoom}</td><br/>
            <tr>ndoubleRoom : ${booking.nDoubleRoom}</td><br/>
            <tr>cost : ${bookingInfo.cost}</tr><br/>
            <tr>discount : ${bookingInfo.discount}</tr><br/>
            <tr>First day of Booking : ${firstDay}</tr><br/>
            <tr>Last day of Booking : ${bookingInfo.date}</tr>
        </table>
    </body>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
</html>
