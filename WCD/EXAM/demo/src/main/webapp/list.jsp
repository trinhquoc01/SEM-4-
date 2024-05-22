<%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/22/2024
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Birthday</th>
        <th>Address</th>
        <th>Position</th>
        <th>Department</th>
    </tr>
    <c:forEach var="employee" items="${listEmployee}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.fullName}</td>
            <td>${employee.birthday}</td>
            <td>${employee.address}</td>
            <td>${employee.position}</td>
            <td>${employee.department}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
