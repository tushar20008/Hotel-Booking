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
    <jsp:include page="../translation.jsp"></jsp:include>
    <body>
        <h3>Edit Manager</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty manager}">
         <form method="POST" action="${pageContext.request.contextPath}/editManager">
            <input type="hidden" name="code" value="${manager.code}" />
            <table border="0">
               <tr>
                  <td>Code</td>
                  <td style="color:red;">${manager.code}</td>
               </tr>
               <tr>
                  <td>UserName</td>
                  <td><input type="text" name="username" value="${manager.userName}" /></td>
               </tr>
               <tr>
                  <td>Password</td>
                  <td><input type="text" name="password" value="${manager.password}" /></td>
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
