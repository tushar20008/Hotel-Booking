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
        <h3>Hotel List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Hotel Id</th>
          <th>Name</th>
          <th>Location</th>
          <th>Edit</th>
<!--          <th>Delete</th>-->
       </tr>
       <c:forEach items="${hotelList}" var="hotel" >
          <tr>
             <td>${hotel.id}</td>
             <td>${hotel.name}</td>
             <td>${hotel.location}</td>
             <td>
                <a href="editHotel?code=${hotel.id}">Edit</a>
             </td>
<!--             <td>
                <a href="deleteHotel?code=${hotel.id}">Delete</a>
             </td>-->
          </tr>
       </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/home?user=manager">Go Back</a>
    </body>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
</html>
