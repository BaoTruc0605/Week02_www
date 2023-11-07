<%@ page import="vn.edu.iuh.fit.week02.models.Employee" %><%--
  Created by IntelliJ IDEA.
  User: BaoTruc
  Date: 11/7/2023
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Detail</title>
</head>
<body>
<% Employee employee=(Employee) request.getAttribute("employee"); %>
<h1>Employee Detail</h1>
<h3>id: <%= employee.getId() %></h3>
<h3>name: <%= employee.getFullName() %></h3>
<h3>Day of birth: <%= employee.getDob() %></h3>
<h3>email: <%= employee.getEmail() %></h3>
<h3>phone: <%= employee.getPhone() %></h3>
<h3>Address: <%= employee.getAddress() %></h3>
<h3>Status: <%= employee.getStatus() %></h3>
</body>
</html>
