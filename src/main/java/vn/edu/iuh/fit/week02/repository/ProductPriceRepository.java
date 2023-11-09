package vn.edu.iuh.fit.week02.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.edu.iuh.fit.week02.factory.MySessionFatory;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.models.ProductPrice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class
ProductPriceRepository {
    private SessionFactory sessionFactory;

    public ProductPriceRepository() {
        this.sessionFactory = MySessionFatory.getInstance().getSessionFactory();
    }

    public void insert(ProductPrice productPrice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(productPrice);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void update(ProductPrice productPrice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(productPrice);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<ProductPrice> getAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<ProductPrice> productPrices = session.createQuery("from ProductPrice ", ProductPrice.class).getResultList();
            transaction.commit();
            return productPrices;
        } catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public List<ProductPrice> getPriceHieuLuc() {
        Transaction transaction = null;
        ProductPrice pTemp = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<ProductPrice> productPrices = new ArrayList<>();
            List<ProductPrice> pList = getAll();
            List<Long> pId = new ArrayList<>();
            System.out.println("65");
            for (ProductPrice p : pList) {
                if (findByProductID(p.getProduct().getId()).size() == 1) {
                    productPrices.add(p);
                    System.out.println("70");
                } else {
                    if (!pId.contains(p.getProduct().getId())) {
                        pTemp = p;
                        System.out.println("73");
                        for (ProductPrice p1 : pList) {
                            System.out.println("75");
                            if (p.getProduct().getId() == p1.getProduct().getId()) {
                                if (pTemp.getDate().isBefore(p1.getDate())) {
                                    pTemp = p1;
                                }
                            }
                        }
                        productPrices.add(pTemp);
                        pId.add(p.getProduct().getId());
                    }
                }
            }
            transaction.commit();
            return productPrices;
        } catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public Optional<ProductPrice> findByID(long productId, LocalDate date) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sql = "from ProductPrice pp where  pp.product.id=:productId and pp.date=:date";
            Query<ProductPrice> query = session.createQuery(sql, ProductPrice.class);
            query.setParameter("productId", productId);
            query.setParameter("date", date);
            ProductPrice productPrice = query.getSingleResult();
            transaction.commit();
            return Optional.ofNullable(productPrice);
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.empty();
    }

    public List<ProductPrice> findByProductID(long productId) {
        Transaction transaction = null;
        List<ProductPrice> pList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sql = "from ProductPrice pp where  pp.product.id=:productId";
            Query<ProductPrice> query = session.createQuery(sql, ProductPrice.class);
            query.setParameter("productId", productId);
            pList = query.getResultList();
            transaction.commit();
            return pList;
        } catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }


}
