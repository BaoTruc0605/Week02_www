<%@ page import="vn.edu.iuh.fit.week02.models.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BaoTruc
  Date: 11/7/2023
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Service</title>
</head>
<body>
<%
    List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
%>
<table>
    <tr>
        <th>Id</th>
        <th>Full name</th>
        <th>Day of birth</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Status</th>
    </tr>
    <% for ( Employee employee : employeeList) { %>
    <tr>
        <td><%=employee.getId()%></td>
        <td><%=employee.getFullName()%></td>
        <td><%=employee.getDob()%></td>
        <td><%=employee.getEmail()%></td>
        <td><%=employee.getPhone()%></td>
        <td><%=employee.getAddress()%></td>
        <td><%=employee.getStatus()%></td>
        <td><a href="Week2?action=view&id=<%=employee.getId()%>">View</a>
            <a href="Week2?action=update&id=<%=employee.getId()%>">Update</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
