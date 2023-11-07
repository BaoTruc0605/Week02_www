<%--
  Created by IntelliJ IDEA.
  User: BaoTruc
  Date: 11/8/2023
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Employee</title>
</head>
<body>
<div class="container">
    <h1>Insert Account</h1>
    <form action="Week2?action=insertEmployee" method="post">
        Full name: <input type="text" name="fullName"><br>
        Date of birth: <input type="date" name="dob"><br>
        Email: <input type="text" name="email"><br>
        Phone: <input type="text" name="phone"><br>
        Address: <input type="text" name="address"><br>
        Status: <select name="status">
        <option value="1">Đang làm việc</option>
        <option value="0">Tạm ngh</option>
        <option value="-1">Nghỉ Việc</option>
    </select><br><br>
        <button type="submit">Thêm</button>
    </form>
</div>

</body>
</html>
