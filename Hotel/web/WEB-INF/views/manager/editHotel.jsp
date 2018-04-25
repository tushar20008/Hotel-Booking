<%-- 
    Document   : editManager
    Created on : 25 Apr, 2018, 12:22:07 PM
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
        <h3>Edit Hotel</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty hotel}">
         <form method="POST" action="${pageContext.request.contextPath}/editHotel">
            <input type="hidden" name="code" value="${hotel.id}" />
            <table border="0">
               <tr>
                  <td>Code</td>
                  <td style="color:red;">${hotel.id}</td>
               </tr>
               <tr>
                  <td>Name</td>
                  <td><input type="text" name="name" value="${hotel.name}" /></td>
               </tr>
               <tr>
                  <td>Location</td>
                  <td><input type="text" name="location" value="${hotel.location}" /></td>
               </tr>
               <tr>
                  <td>Discount</td>
                  <td><input type="number" name="discount" value="${hotel.discount}" /></td>
               </tr>
               <tr>
                  <td>Number of Single Rooms</td>
                  <td><input type="number" name="singleRoomCap" value="${hotel.singleRoomCap}" /></td>
               </tr>
               <tr>
                  <td>Price of Single Room</td>
                  <td><input type="number" name="singleRoomPrice" value="${hotel.singleRoomPrice}" /></td>
               </tr>
               <tr>
                  <td>Number of Double Rooms</td>
                  <td><input type="number" name="doubleRoomCap" value="${hotel.doubleRoomCap}" /></td>
               </tr>
               <tr>
                  <td>Price of Double Room</td>
                  <td><input type="number" name="doubleRoomPrice" value="${hotel.doubleRoomPrice}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/hotelList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
    </body>
</html>
