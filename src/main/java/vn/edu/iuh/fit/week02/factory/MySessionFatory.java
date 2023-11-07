package vn.edu.iuh.fit.week02.factory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import vn.edu.iuh.fit.week02.converter.ConvertEmployeeStatus;
import vn.edu.iuh.fit.week02.converter.ConvertProductStatus;
import vn.edu.iuh.fit.week02.models.*;

public class MySessionFatory {
    private static MySessionFatory instance = null;
    private SessionFactory sessionFactory = null;
    private MySessionFatory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductImage.class)
                .addAnnotatedClass(ProductPrice.class)
                .addAnnotatedClass(ConvertEmployeeStatus.class)
                .addAnnotatedClass(ConvertProductStatus.class)
                .getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    public static MySessionFatory getInstance(){
        if(instance==null)
            instance = new MySessionFatory();
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
