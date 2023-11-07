<%@ page import="vn.edu.iuh.fit.week02.models.Employee" %>
<%@ page import="vn.edu.iuh.fit.week02.status.EmployeeStatus" %><%--
  Created by IntelliJ IDEA.
  User: BaoTruc
  Date: 11/8/2023
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
<div class="container">
    <h1>Update Account</h1>
    <% Employee employee=(Employee) request.getAttribute("employee"); %>
    <form action="Week2?action=updateEmployee" method="post">
        <br>Id: <input type="text" name="id" readonly value="<%= employee.getId() %>"><br>
        Full name: <input type="text" name="fullName" value="<%= employee.getFullName() %>"><br>
        Date of birth: <input type="date" name="dob" value="<%= employee.getDob() %>"><br>
        Email: <input type="text" name="email" value="<%= employee.getEmail() %>"><br>
        Phone: <input type="text" name="phone" value="<%= employee.getPhone() %>"><br>
        Address: <input type="text" name="address" value="<%= employee.getAddress() %>"><br>
        Status: <select name="status">
        <%if(employee.getStatus().equals(EmployeeStatus.DangLamViec)){%>
        <option value="1" selected>Đang làm việc</option>
        <option value="0">Tạm nghỉ</option>
        <option value="-1">Nghỉ Việc</option>
        <%}%>
        <%if(employee.getStatus().equals(EmployeeStatus.TamNghi)){%>
        <option value="0" selected>Tạm nghỉ</option>
        <option value="1">Đang làm việc</option>
        <option value="-1">Nghỉ Việc</option>
        <%}%>
        <%if(employee.getStatus().equals(EmployeeStatus.NghiViec)){%>
        <option value="1" >Đang làm việc</option>
        <option value="0">Tạm nghỉ</option>
        <option value="-1" selected>Nghỉ Việc</option>
        <%}%>
    </select><br><br>
        <button type="submit">Update</button>
    </form>
</div>

</body>
</html>
