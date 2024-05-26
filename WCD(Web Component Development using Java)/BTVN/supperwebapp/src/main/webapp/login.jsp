<%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/10/2024
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Login form</h3>

    <form method="post" action="loginController.jsp">
        UserName : <input type="text" name="username" id="username"> <br>
        PassWord : <input type="password" name="password" id="password"> <br>
        <input type="submit" value="login">
    </form>
</body>
</html>
