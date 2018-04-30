<%-- 
    Document   : updateManager
    Created on : 25 Apr, 2018, 10:43:11 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager List</title>
    </head>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Manager List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Manger Id</th>
          <th>Username</th>
          <th>Password</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${managerList}" var="manager" >
          <tr>
             <td>${manager.code}</td>
             <td>${manager.userName}</td>
             <td>${manager.password}</td>
             <td>
                <a href="editManager?code=${manager.code}">Edit</a>
             </td>
             <td>
                <a href="deleteManager?code=${manager.code}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/home?user=admin">Go Back</a>
    </body>
    <jsp:include page="../_demoTag.jsp"></jsp:include>
</html>
