<%-- 
    Document   : editManager
    Created on : 25 Apr, 2018, 12:22:07 PM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Recommend Hotel</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty member}">
         <form method="POST" action="${pageContext.request.contextPath}/recommendHotel">
            <input type="hidden" name="code" value="${member.code}" />
            <table border="0">
               <tr>
                  <td>Code</td>
                  <td style="color:red;">${member.code}</td>
               </tr>
               <tr>
                   <td colspan = "2">
                       <select name ="hotel">
                           <c:forEach items="${hotelList}" var="hotel" >
                               <option value=${hotel.id}>${hotel.name}</option>
                           </c:forEach>
                       </select>
                   </td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/memberList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
    </body>
</html>
