<%@ page import="com.example.supperwebapp.LoginBean" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/10/2024
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginController</title>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        boolean status = false;
        status = loginBean.checkLogin(username, password);

        if (status) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("fail.jsp");
        }
     %>

</head>
<body>

</body>
</html>
