<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week02.models.*" %><%--
  Created by IntelliJ IDEA.
  User: BaoTruc
  Date: 11/8/2023
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table {
            border-collapse: collapse; /* Loại bỏ khoảng trắng giữa các đường viền */
            width: 100%;
        }

        table, th, td {
            border: 1px solid black; /* Đặt đường viền 1px đen cho bảng, tiêu đề và ô dữ liệu */
        }

        th {
            background-color: aquamarine;
        }
    </style>
    <script>
        // document.getElementById("orderSelect").onchange = function () {
        //     var selectedValue = this.value;
        //     var newURL = "Week2?action=getOrder&id=" + selectedValue;
        //     window.location.href = newURL;
        // };
        function redirectToNewURL() {
            var selectedValue = document.getElementById("orderSelect").value;
            // Thực hiện bất kỳ xử lý nào bạn cần ở đây với selectedValue
            // Sau đó, chuyển hướng đến URL mới
            var newURL = "Week2?action=getOrder&id=" + selectedValue; // Thay thế "newURL" bằng URL bạn muốn chuyển hướng
            window.location.href = newURL;
        }
    </script>
    <title>Lập hóa đơn</title>
</head>
<body>
<div class="container">

    <%
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
    %>
    <%
        List<Product> productList = (List<Product>) request.getAttribute("products");
    %>
    <%
        List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
    %>
    <%
        List<Orders> orderList = (List<Orders>) request.getAttribute("orders");
    %>
    <%
        List<OrderDetail> orderDetailList = (List<OrderDetail>) request.getAttribute("orderDetail");
    %>

    <h1>Insert Account</h1>
    <form method="post">
        Order: <select name="orders" id="orderSelect" onchange="redirectToNewURL()">
        <% for (Orders orders : orderList) { %>
        <option value=<%= orders.getId() %>><%= orders.getCustomer().getName()%>_<%= orders.getId() %>
        </option>
        <%}%><br>
    </select>
        Employee: <select name="employee">
        <% for (Employee employee : employeeList) { %>
        <option value=<%= employee.getId() %>><%= employee.getPhone() %>_<%= employee.getFullName() %>
        </option>
        <%}%>
    </select>
        Customer: <select name="customer">
        <% for (Customer customer : customerList) { %>
        <option value=<%= customer.getId() %>><%= customer.getPhone() %>_<%= customer.getName() %>
        </option>
        <%}%>
    </select>
        Order date: <input type="date" name="dob"><br>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Unit</th>
                <th>Manufacturer</th>
                <th>Số lượng mua</th>
            </tr>
                <%int soLuong=0;%>
                <% for ( Product product : productList) { %>
            <tr>
                <td><%=product.getId()%>
                </td>
                <td><%=product.getName()%>
                </td>
                <td><%=product.getDescription()%>
                </td>
                <td><%=product.getUnit()%>
                </td>
                <td><%=product.getManufacturerName()%>
                </td>
                <% if(orderDetailList!=null) { %>
                <% for (OrderDetail orderDetail : orderDetailList) { %>
                <%if (product.getId() == orderDetail.getProduct().getId()) {%>
                <%soLuong = orderDetail.getQuantity();%>
                <%}%><%}%><%}%>
                <td><%=soLuong%>
                </td>

                <td><input type="text" name="slThem">
                    <button type="submit" action="Week2?action=insertOrder">Thêm</button>
                </td>
                <td>
                    <button type="submit" action="Week2?action=deleteOrder">Xóa</button>
                </td>
            </tr>
                <% } %>


    </form>
</div>

</body>
</html>
