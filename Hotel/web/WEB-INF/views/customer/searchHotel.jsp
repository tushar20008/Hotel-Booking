<%-- 
    Document   : search
    Created on : 25 Apr, 2018, 10:39:26 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotels</title>
    </head>
    <body>
        <form name="Hotel Search" method="POST">
            Location: <input type="text" name="location" name="location"/></br>
            Start Date: <input type="date" name="startdate" value=""/></br>
            End Date: <input type="date" name="enddate" value=""/></br>
            Single Rooms: <select name="singleRooms" id="singleRooms" size="5">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select></br>
            Double Rooms: <select name="doubleRooms" id="doubleRooms" size="5">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select></br>
            <input type="submit" value="Search" name="Search"/>
        </form>
        <p style="color: red;">${errorString}</p>
        
         <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Hotel Name</th>
          <th>Location</th>
          <th>Single Room Price</th>
          <th>Double Room Price</th>
          <th></th>
       </tr>
       <c:forEach items="${availableHotelsList}" var="hotel" >
          <tr>
             <td>${hotel.name}</td>
             <td>${hotel.location}</td>
             <td>${hotel.singleRoomPrice}</td>
             <td>${hotel.doubleRoomPrice}</td>
             <td>
                <a href="checkout?hotelId=${hotel.id}">Book</a>
             </td>
          </tr>
       </c:forEach>
    </table>
             
    </body>
</html>
