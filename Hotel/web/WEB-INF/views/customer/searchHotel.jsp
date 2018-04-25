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
        <form name="Hotel Search" action="searchHotel.jsp">
            <select name="Location" size="5">
                <option>Hong Kong</option>
                <option>London</option>
                <option>New York</option>
                <option>Tokyo</option>
                <option>Delhi</option>
            </select>
            <select name="Single Rooms" size="5">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <select name="Double Rooms" size="5">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            
        </form>
    </body>
</html>
