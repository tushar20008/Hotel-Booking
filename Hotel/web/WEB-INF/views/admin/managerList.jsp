<%-- 
    Document   : updateManager
    Created on : 25 Apr, 2018, 10:43:11 AM
    Author     : Tushar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager List</title>
    </head>
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
             <td>${manager.id}</td>
             <td>${manager.username}</td>
             <td>${manager.password}</td>
             <td>
                <a href="editManager?code=${manager.id}">Edit</a>
             </td>
             <td>
                <a href="deleteManager?code=${manager.id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
    </body>
</html>
