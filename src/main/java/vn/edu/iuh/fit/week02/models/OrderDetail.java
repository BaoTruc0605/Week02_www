package vn.edu.iuh.fit.week02.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders orders;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    @Column(length = 10, nullable = false)
    private int quantity;
    @Column(length = 20, nullable = false)
    private double price;
    @Column(columnDefinition = "nvarchar(255)")
    private String note;

    public OrderDetail() {
    }

    public OrderDetail(Orders orders, Product product, int quantity, double price, String note) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orders=" + orders +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }
}
