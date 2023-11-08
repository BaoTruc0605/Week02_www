package vn.edu.iuh.fit.week02.services;
import vn.edu.iuh.fit.week02.models.Customer;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.repository.CustomerRepository;
import vn.edu.iuh.fit.week02.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(){
        customerRepository = new CustomerRepository();
    }

    public void insert(Customer customer){
        customerRepository.insert(customer);
    }
    public void update(Customer customer){
        customerRepository.update(customer);
    }
    public List<Customer> getAll(){
        return customerRepository.getAll();
    }

    public Optional<Customer> findByID(long id) {
        return customerRepository.findByID(id);
    }
}

