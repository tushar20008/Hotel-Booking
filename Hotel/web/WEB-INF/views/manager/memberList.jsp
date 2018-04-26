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
        <title>Member List</title>
    </head>
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Member List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Member Id</th>
          <th>Username</th>
          <th>Password</th>
          <th>Recommend</th>
       </tr>
       <c:forEach items="${memberList}" var="member" >
          <tr>
             <td>${member.code}</td>
             <td>${member.userName}</td>
             <td>${member.password}</td>
             <td>
                <a href="recommendHotel?code=${member.code}">Recommend</a>
             </td>
          </tr>
       </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/home?user=manager">Go Back</a>
    </body>
</html>
