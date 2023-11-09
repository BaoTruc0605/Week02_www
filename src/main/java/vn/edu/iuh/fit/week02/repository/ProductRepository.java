package vn.edu.iuh.fit.week02.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.status.ProductStatus;
import vn.edu.iuh.fit.week02.models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class
ProductRepository {
    private SessionFactory sessionFactory;
    private ProductPriceRepository productPriceRepository = new ProductPriceRepository();

    public ProductRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }
    public void insert(Product product){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public void update(Product product){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();
        }
    }
    public List<Product> getAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Product> product = session.createQuery("from Product ", Product.class).getResultList();
            transaction.commit();
            return product;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }
    public List<Product> getProductCoTheGiaoDich(){
        Transaction transaction = null;
        List<Product> products = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql = "select p from Product p where p.status = :status and p.unit>0";
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("status", ProductStatus.DangKinhDoanh);
            products = query.getResultList();
            transaction.commit();
            return products;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }
    public List<Product> getProductByOrder(long id){
        Transaction transaction = null;
        List<Product> products = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql = "select Product from OrderDetail o where o.orders.id = :id";
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("id", id);
            products = query.getResultList();
            transaction.commit();
            return products;
        }catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<Product> findByID(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            transaction.commit();
            return Optional.ofNullable(product);
        }catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }

    public Map<Product, ProductPrice> getProductCoTheGiaoDichVaPrice(){
        Transaction transaction = null;
        Map<Product, ProductPrice> productAndPrice = new HashMap<>();
        List<Product> pList = getProductCoTheGiaoDich();
        List<ProductPrice> productPriceList = productPriceRepository.getPriceHieuLuc();
        for(Product p: pList){
            for(ProductPrice productPrice:productPriceList){
                if(p.getId()==productPrice.getProduct().getId()){
                    productAndPrice.put(p,productPrice);
                }
            }
        }
        return productAndPrice;
    }
    public void giamUnit( long productId,int soLuong){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Product p = findByID(productId).get();
            if (p != null) {
                // Giảm giá trị số lượng của product
                int newUnit = p.getUnit() - soLuong; // Giảm số lượng
                p.setUnit(newUnit);
                // Cập nhật đối tượng vào cơ sở dữ liệu
                session.update(p);
            }
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }
    }



}
