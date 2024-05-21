<%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/20/2024
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" >
        <div>
            <a href=""> User Management App </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
                <form action="update" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user != null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                    </c:if>
                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text" VALUE="<c:out value='${user.name}'/> " class="form-control" name="name" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text" VALUE="<c:out value='${user.name}'/> " class="form-control" name="email">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text" VALUE="<c:out value='${user.name}'/> " class="form-control" name="country">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
