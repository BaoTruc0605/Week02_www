package vn.edu.iuh.fit.week02.models;
import jakarta.persistence.*;
import vn.edu.iuh.fit.week02.status.ProductStatus;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = 20)
    private long id;
    @Column(nullable = false,columnDefinition = "nvarchar(255)")
    private String name;
    @Column(nullable = false,columnDefinition = "nvarchar(255)")
    private String description;
    @Column(nullable = false)
    private int unit;
    @Column(nullable = false,columnDefinition = "nvarchar(255)", name ="nanufacturer_name")
    private String manufacturerName;
//    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;

    public Product() {
    }

    public Product(String name, String description, int unit, String manufacturerName, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturerName = manufacturerName;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit='" + unit + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", status=" + status +
                '}';
    }
}
