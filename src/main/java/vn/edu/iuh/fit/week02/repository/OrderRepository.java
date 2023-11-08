package vn.edu.iuh.fit.week02.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.models.Orders;
import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.status.ProductStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class
OrderRepository {
    private SessionFactory sessionFactory;

    public OrderRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }
    public void insert(Orders orders){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(orders);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public void update(Orders orders){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(orders);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public List<Orders> getAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Orders> orders = session.createQuery("from Orders ", Orders.class).getResultList();



            transaction.commit();
            return orders;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<Orders> findByID(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Orders order = session.get(Orders.class, id);
            transaction.commit();
            return Optional.ofNullable(order);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }
    public Map<Orders, OrderDetail> findByIDDetail(long id) {
        Transaction transaction = null;
        Orders o = null;
        Map<Orders, OrderDetail> resultMap = new HashMap<>();


        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql ="from Orders o join OrderDetail od on o.id = od.id where o.id = :id";
            Query<Orders> query1 = session.createQuery(sql, Orders.class);
            Query<OrderDetail> query2 = session.createQuery(sql, OrderDetail.class);
            query1.setParameter("id", id);
            query2.setParameter("id", id);
            resultMap.put(query1.getSingleResult(),query2.getSingleResult());
            transaction.commit();
            return resultMap;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }
    public Optional<Orders> kiemTraTonTai(Orders orders) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            for(Orders o : getAll()){
                if(o.getCustomer().getId()==orders.getCustomer().getId() && o.getEmployee().getId()==orders.getEmployee().getId() && o.getDate().equals(orders.getDate())){
                    return Optional.ofNullable(o);
                }
            }
            transaction.commit();
            return Optional.empty();
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }


}
