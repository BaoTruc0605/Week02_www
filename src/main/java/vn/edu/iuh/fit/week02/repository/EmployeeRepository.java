package vn.edu.iuh.fit.week02.repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.Employee;
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
            List<Employee> candidates = session.createQuery("from Employee ", Employee.class).getResultList();

            transaction.commit();
            return candidates;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<Employee> findByID(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Employee candidates = session.get(Employee.class, id);
            transaction.commit();
            return Optional.ofNullable(candidates);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }


}
