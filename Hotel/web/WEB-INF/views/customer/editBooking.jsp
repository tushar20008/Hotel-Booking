<%-- 
    Document   : editBooking
    Created on : 26 Apr, 2018, 9:55:06 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Edit Booking</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty booking}">
         <form method="POST" action="${pageContext.request.contextPath}/editBooking">
            <input type="hidden" name="code" value="${booking.bookingId}" />
            <table border="0">
               <tr>
                  <td>Booking Id</td>
                  <td style="color:red;">${booking.bookingId}</td>
               </tr>
               <tr>
                  <td>Number of Single Rooms</td>
                  <td><input type="number" name="nSingleRoom" value="${booking.nSingleRoom}" /></td>
               </tr>
               <tr>
                  <td>Number of Double Rooms</td>
                  <td><input type="number" name="nDoubleRoom" value="${booking.nDoubleRoom}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/managerList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
    </body>
</html>
