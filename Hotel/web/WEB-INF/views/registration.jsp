<%-- 
    Document   : registration
    Created on : Apr 26, 2018, 2:30:08 AM
    Author     : Aditya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <jsp:include page="_demoTag.jsp"></jsp:include>
        <h1>Registration Page</h1>
        <p style="color: red;">${errorString}</p>
        
        <form method="POST" action="${pageContext.request.contextPath}/registration">
          <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value= "${user.password}" /> </td>
            </tr> 
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
        </form>    
        <jsp:include page="_demoTag.jsp"></jsp:include>
    </body>
</html>
