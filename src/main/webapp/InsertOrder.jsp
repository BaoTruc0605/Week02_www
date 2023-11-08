<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week02.models.*" %>
<%@ page import="javax.lang.model.element.Element" %><%--
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

        th, td {
            cursor: pointer;
        }

        th {
            background-color: aquamarine;
        }

        tr.selected {
            background-color: aqua;
        }



    </style>



    <title>Lập hóa đơn</title>
</head>
<body>
<div class="container">
    <%int soLuong = 0;%>

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
    <%--    <%--%>
    <%--        List<OrderDetail> orderDetailList = (List<OrderDetail>) request.getAttribute("orderDetail");--%>
    <%--    %>--%>

    <h1>Insert Account</h1>
    <form method="post" action="Week2?action=insertOrder">
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
        Order date: <input type="date" name="orderDate"><br>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Unit</th>
                <th>Manufacturer</th>
                <th>Số lượng mua</th>
            </tr>
            </thead>
            <tbody>
            <% for (Product product : productList) { %>
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
                <td><%=soLuong%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <input type="hidden" name="selectedRow" value="">
        <input type="text" name="slThem" id="slThem">
        <button type="submit">Thêm</button>
        <a href="Week2?action=updateOrder">Sửa</a>
    </form>

</div>
<script>
    var tableRows = document.querySelectorAll('tbody tr');
    var selectedRowInput = document.querySelector('input[name="selectedRow"]');

    tableRows.forEach(function(row) {
        row.addEventListener('click', function() {
            // Xóa chọn trước đó nếu có
            var selectedRow = document.querySelector('tr.selected');
            if (selectedRow) {
                selectedRow.classList.remove('selected');
            }

            // Đánh dấu hàng hiện tại là đã chọn
            row.classList.add('selected');

            // Lấy giá trị của cột đầu tiên trong hàng đã chọn
            var firstCell = row.querySelector('td:first-child');
            var firstCellValue = firstCell.textContent;

            // Lưu giá trị hàng đã chọn vào trường ẩn
            selectedRowInput.value = firstCellValue;
        });
    });
</script>
</body>
</html>
