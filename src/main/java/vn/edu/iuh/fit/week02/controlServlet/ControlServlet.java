package vn.edu.iuh.fit.week02.controlServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.services.EmployeeService;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet(urlPatterns = {"/Week2"})
public class ControlServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.equals("insertEmployee")) {
                    insertAccount(req, resp);
                }

                if (action.equals("updateEmployee")) {
                    updateEmployee(req, resp);
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
            }
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

    public void insertAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

}
