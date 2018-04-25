<%-- 
    Document   : home
    Created on : 25 Apr, 2018, 10:40:00 AM
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
        <h1>Manager Home World!</h1>
        <a href="${pageContext.request.contextPath}/memberList">Member List</a>
        <a href="${pageContext.request.contextPath}/hotelList">Hotel List</a>
    </body>
</html>
