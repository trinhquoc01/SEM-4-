<%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/20/2024
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
  <title>User Management Application</title>
  Θ <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-gg0yR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQU0hcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" >
    <div>
      <a href="" class="navbar-brand"> User Management App </a>
    </div>
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="row">
  <div class="container">
    <h3 class="text-center">List of Users</h3>
    <hr>
    <div class="container text-left">
      <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
        New User</a>
    </div>
    <br>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Country</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${listUser}">
        <tr>
          <td>
            <c:out value="${user.id}" />
          </td>
          <td>
            <c:out value="${user.name}" />
          </td>
          <td>
            <c:out value="${user.email}" />
          </td>
          <td>
            <c:out value="${user.country}" />
          </td>
          <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
            <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
        </tr>
      </c:forEach>

      </tbody>
    </table>
  </div>
</div>
</body>
</html>
