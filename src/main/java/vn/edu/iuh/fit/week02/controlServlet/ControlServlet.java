package vn.edu.iuh.fit.week02.controlServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.week02.models.*;
import vn.edu.iuh.fit.week02.services.*;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.ToIntFunction;


@WebServlet(urlPatterns = {"/Week2"})
public class ControlServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();
    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();
    private OrderService orderService = new OrderService();
    private OrderDetailService orderDetailService = new OrderDetailService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.equals("insertEmployee")) {
                    insertEmployee(req, resp);
                }
                if (action.equals("updateEmployee")) {
                    updateEmployee(req, resp);
                }
                if (action.equals("insertOrder")) {
                    insertOrder(req, resp);
                }
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action.equals("employees")) {
                viewEmployee(req, resp);
            }
            if (action.equals("view")) {
                viewEmployeeDetail(req, resp);
            }
            if (action.equals("update")) {
                update(req, resp);
            }if (action.equals("showOrders")) {
                showOrders(req, resp);
            }
//            if (action.equals("getOrder")) {
//                getOrders(req, resp);
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void viewEmployeeDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        try {
            long id = Long.parseLong(req.getParameter("id"));
            Employee employee = employeeService.findByID(id).get();
            req.setAttribute("employee", employee);
            url = "/Employee_Detail.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    private void viewEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        try {
            req.setAttribute("employees", employeeService.getAll());
            url = "/Employee.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    public void insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int statusValue = Integer.parseInt(req.getParameter("status"));
        EmployeeStatus status;
        if (statusValue == 1)
            status = EmployeeStatus.DangLamViec;
        else if (statusValue == 0)
            status = EmployeeStatus.TamNghi;
        else
            status = EmployeeStatus.NghiViec;
        Employee e = new Employee(fullName, dob, email, phone, address, status);
        employeeService.insert(e);
        resp.sendRedirect("index.jsp");
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        Employee employee = employeeService.findByID(id).get();
        String fullName = req.getParameter("fullName");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int statusValue = Integer.parseInt(req.getParameter("status"));
        EmployeeStatus status;
        if (statusValue == 1)
            status = EmployeeStatus.DangLamViec;
        else if (statusValue == 0)
            status = EmployeeStatus.TamNghi;
        else
            status = EmployeeStatus.NghiViec;
        employee.setFullName(fullName);
        employee.setDob(dob);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAddress(address);
        employee.setStatus(status);
        employeeService.update(employee);
        resp.sendRedirect("index.jsp");


    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        try {
            long id = Long.parseLong(req.getParameter("id"));
            Employee employee = employeeService.findByID(id).get();
            req.setAttribute("employee", employee);
            url = "/UpdateEmployee.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);

    }
    private void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        try {
            req.setAttribute("employees", employeeService.getEmployeeDangLam());
            req.setAttribute("customers", customerService.getAll());
            req.setAttribute("products", productService.getProductCoTheGiaoDich());
            req.setAttribute("orders", orderService.getAll());
            req.setAttribute("productPrice", productService.getProductCoTheGiaoDichVaPrice());
            req.setAttribute("orderDetail",orderDetailService.getAll());



            url = "/InsertOrder.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

//    private void getOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = "";
//        List<Orders> o = new ArrayList<>();
//        List<Customer> c = new ArrayList<>();
//        List<Employee> em = new ArrayList<>();
//        List<OrderDetail> od = new ArrayList<>();
//
//        try {
//            long id = Long.parseLong(req.getParameter("id"));
//            Optional<Orders> orders = orderService.findByID(id);
//            o.add(orderService.findByID(id).get());
//            req.setAttribute("orders", o);
//            em.add(orders.get().getEmployee());
//            req.setAttribute("employees", em);
//            c.add(orders.get().getCustomer());
//            req.setAttribute("customers", c);
//            req.setAttribute("products", productService.getProductByOrder(orders.get().getId()));
//            od.add(orderService.findByIDDetail(id).get(orders));
//            req.setAttribute("orderDetail", od);
//
//
//            url = "/InsertOrder.jsp";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(req, resp);
//    }

//    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long id = Long.parseLong(req.getParameter("id"));
//        Employee employee = employeeService.findByID(id).get();
//        String fullName = req.getParameter("fullName");
//        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
//        String email = req.getParameter("email");
//        String phone = req.getParameter("phone");
//        String address = req.getParameter("address");
//        int statusValue = Integer.parseInt(req.getParameter("status"));
//        EmployeeStatus status;
//        if (statusValue == 1)
//            status = EmployeeStatus.DangLamViec;
//        else if (statusValue == 0)
//            status = EmployeeStatus.TamNghi;
//        else
//            status = EmployeeStatus.NghiViec;
//        employee.setFullName(fullName);
//        employee.setDob(dob);
//        employee.setEmail(email);
//        employee.setPhone(phone);
//        employee.setAddress(address);
//        employee.setStatus(status);
//        employeeService.update(employee);
//        resp.sendRedirect("index.jsp");
//
//
//    }

    public void insertOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("slThem"));

        Optional<Employee> employee = employeeService.findByID(Long.parseLong(req.getParameter("employee")));
        Optional<Customer> customer = customerService.findByID(Long.parseLong(req.getParameter("customer")));
        LocalDate orderDate = LocalDate.parse(req.getParameter("orderDate"));

        System.out.println(employee.toString());
        Orders o = new Orders(orderDate,employee.get(),customer.get());
        Optional<Product> product = productService.findByID(Long.parseLong(req.getParameter("selectedRow").trim()));
        double price = Double.parseDouble(req.getParameter("priceSelect").trim());
        String note = req.getParameter("note");
        if(quantity>product.get().getUnit()){
            quantity = product.get().getUnit();
        }

        OrderDetail orderDetail = new OrderDetail(o,product.get(),quantity,price*quantity,note);

        if(orderService.kiemTraTonTai(o).isEmpty()){
            orderService.insert(o);
            orderDetailService.insert(orderDetail);
            productService.giamUnit(product.get().getId(),quantity);
        }
        else{
            o.setId(orderService.kiemTraTonTai(o).get().getId());
            orderService.update(o);
            productService.giamUnit(product.get().getId(),quantity);
            if(orderDetailService.kiemTraTonTai(orderDetail).isEmpty()){
                orderDetailService.insert(orderDetail);
            }else {
                orderDetailService.tangSoLuong(product.get().getId(),orderDetailService.kiemTraTonTai(orderDetail).get().getOrders().getId(),quantity, price*quantity);
            }
        }
        resp.sendRedirect("index.jsp");
    }


}
