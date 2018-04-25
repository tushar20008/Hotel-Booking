<%-- 
    Document   : search
    Created on : 25 Apr, 2018, 10:39:26 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotels</title>
    </head>
    <body>
        <form name="Hotel Search" method="POST">
            Location: <input type="text" id="location" name="Location"/></br>
            Start Date: <input type="date" id="startdate" value=""/></br>
            End Date: <input type="date" id="enddate" value=""/></br>
            <input type="submit" value="Search" name="Search"/>
        </form>
    </body>
</html>
