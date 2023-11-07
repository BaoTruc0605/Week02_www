package vn.edu.iuh.fit.week02.demo;

import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.repository.EmployeeRepository;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.insert(new Employee("Trần Bảo Trúc", LocalDate.now(),"baotruc@gmail.com","0338030540","Nguyễn Thái Sơn, Phuường 4, Gò Vấp, HCM", EmployeeStatus.TamNghi));
    }

}
