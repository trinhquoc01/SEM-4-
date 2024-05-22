<%--
  Created by IntelliJ IDEA.
  User: Trinh Quoc
  Date: 5/22/2024
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Add Employee</h2>
    <form action="insert" method="post">
        Full Name: <input type="text" name="fullname" required /><br/>
        Birthday: <input type="date" name="birthday" required /><br/>
        Address: <input type="text" name="address" required /><br/>
        Position: <input type="text" name="position" required /><br/>
        Department: <input type="text" name="department" required /><br/>
        <input type="submit" value="Submit" />
        <input type="reset" value="Reset" />
    </form>
</head>
<body>

</body>
</html>
