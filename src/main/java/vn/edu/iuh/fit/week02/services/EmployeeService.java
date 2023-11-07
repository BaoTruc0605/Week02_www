package vn.edu.iuh.fit.week02.services;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeService(){
        employeeRepository = new EmployeeRepository();
    }

    public void insert(Employee employee){
        employeeRepository.insert(employee);
    }
    public void update(Employee employee){
        employeeRepository.insert(employee);
    }
    public List<Employee> getAll(){
        return employeeRepository.getAll();
    }

    public Optional<Employee> findByID(long id) {
        return employeeRepository.findByID(id);
    }
}

