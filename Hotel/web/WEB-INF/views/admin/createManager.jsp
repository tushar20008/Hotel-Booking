<%-- 
    Document   : createManager
    Created on : 25 Apr, 2018, 11:58:55 AM
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
              <h3>Create Manager</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createManager">
         <table border="0">
            <tr>
               <td>UserName</td>
               <td><input type="text" name="username" value="${manager.userName}" /></td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value="${manager.password}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="managerList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
    </body>
</html>
