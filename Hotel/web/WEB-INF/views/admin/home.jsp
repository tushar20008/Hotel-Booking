<%-- 
    Document   : home
    Created on : 25 Apr, 2018, 10:42:31 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Functionalities</title>
    </head>
    <body>
        <h1>Admin Home World!</h1>
        <a href="${pageContext.request.contextPath}/managerList">Manager List</a>
        <a href="${pageContext.request.contextPath}/createManager">Create Manager</a>
    </body>
</html>
