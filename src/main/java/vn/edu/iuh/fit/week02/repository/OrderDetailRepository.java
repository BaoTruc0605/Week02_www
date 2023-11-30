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

import java.util.List;
import java.util.Optional;

public class
OrderDetailRepository {
    private SessionFactory sessionFactory;

    public OrderDetailRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }
    public void insert(OrderDetail orderDetail){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(orderDetail);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public void update(OrderDetail orderDetail){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(orderDetail);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public List<OrderDetail> getAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<OrderDetail> orderDetails = session.createQuery("from OrderDetail ", OrderDetail.class).getResultList();

            transaction.commit();
            return orderDetails;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<OrderDetail> findByID(long productId, long orderId) {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql = "from OrderDetail od where od.orders.id=:orderId and od.product.id=:productId";
            Query<OrderDetail> query = session.createQuery(sql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            query.setParameter("productId", productId);
            OrderDetail orderDetail = query.getSingleResult();
            transaction.commit();
            return Optional.ofNullable(orderDetail);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }
    public Optional<OrderDetail> kiemTraTonTai(OrderDetail orderDetail) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            for(OrderDetail od : getAll()){
                if(od.getProduct().getId()==orderDetail.getProduct().getId()&&od.getOrders().getId()==orderDetail.getOrders().getId()){
                    return Optional.ofNullable(od);
                }
            }
            transaction.commit();
            return Optional.empty();
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }
    public void tangSoLuong( long productId,long orderId,int soLuong, double tien){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            OrderDetail orderDetail = findByID(productId,orderId).get();
            if (orderDetail != null) {
                // Tăng giá trị số lượng của OrderDetail
                int newQuantity = orderDetail.getQuantity() + soLuong; // Tăng số lượng
                orderDetail.setQuantity(newQuantity);
                double newTien = orderDetail.getPrice() + tien;
                orderDetail.setPrice(newTien);
                // Cập nhật đối tượng vào cơ sở dữ liệu
                session.update(orderDetail);
            }
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }
    }




}
