package vn.edu.iuh.fit.week02.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.Customer;
import vn.edu.iuh.fit.week02.models.Employee;

import java.util.List;
import java.util.Optional;

public class
CustomerRepository {
    private SessionFactory sessionFactory;

    public CustomerRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }
    public void insert(Customer customer){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public void update(Customer customer){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public List<Customer> getAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Customer> customer = session.createQuery("from Customer ", Customer.class).getResultList();

            transaction.commit();
            return customer;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<Customer> findByID(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            transaction.commit();
            return Optional.ofNullable(customer);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }


}
