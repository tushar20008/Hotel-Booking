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
    <body>
        <body>
        <h3>Checkout Success</h3>
 
        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>Booking ID : ${bookingInfo.bookingId}</tr>
            <tr>username : ${bookingInfo.username}</tr>
            <tr>hotelId : ${bookingInfo.hotelId}</tr>
            <tr>nSingleRoom : ${bookingInfo.nSingleRoom}</tr>
            <tr>nDoubleRoom : ${bookingInfo.nDoubleRoom}</tr>
            <tr>cost : ${bookingInfo.cost}</tr>
            <tr>First day of Booking : ${firstDay}</tr>
            <tr>Last day of Booking : ${bookingInfo.date}</tr>
        </table>
    </body>
</html>
