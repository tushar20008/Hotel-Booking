<%-- 
    Document   : home
    Created on : 25 Apr, 2018, 10:38:40 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Home Page</title>
    </head>
    <body>
        <h1>Customer Functions</h1>
        <a href="${pageContext.request.contextPath}/searchHotel">Search Hotels</a>
        <a href="${pageContext.request.contextPath}/modifyBooking">Modify Bookings</a>
        <a href="${pageContext.request.contextPath}/recommendedHotels">Recommended Hotels</a>
    </body>
</html>
