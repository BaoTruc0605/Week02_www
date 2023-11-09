package vn.edu.iuh.fit.week02.repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;
import vn.edu.iuh.fit.week02.status.ProductStatus;

import java.util.List;
import java.util.Optional;

public class
EmployeeRepository{
    private SessionFactory sessionFactory;

    public EmployeeRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }
    public void insert(Employee employee){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public void update(Employee employee){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public List<Employee> getAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Employee> employee = session.createQuery("from Employee ", Employee.class).getResultList();

            transaction.commit();
            return employee;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<Employee> findByID(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            transaction.commit();
            return Optional.ofNullable(employee);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }
    public List<Employee> getEmployeeDangLam(){
        Transaction transaction = null;
        List<Employee> employees = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql = "select e from Employee e where e.status = :status";
            Query<Employee> query = session.createQuery(sql, Employee.class);
            query.setParameter("status", EmployeeStatus.DangLamViec);
            employees = query.getResultList();
            transaction.commit();
            return employees;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }


}
