package vn.edu.iuh.fit.week02.services;
import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService {
    private ProductRepository productRepository;
    public ProductService(){
        productRepository = new ProductRepository();
    }

    public void insert(Product product){
        productRepository.insert(product);
    }
    public void update(Product product){
        productRepository.update(product);
    }
    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public List<Product> getProductCoTheGiaoDich(){
        return productRepository.getProductCoTheGiaoDich();
    }
    public Optional<Product> findByID(long id) {
        return productRepository.findByID(id);
    }
    public List<Product> getProductByOrder(long id){
        return productRepository.getProductByOrder(id);
    }
    public Map<Product, ProductPrice> getProductCoTheGiaoDichVaPrice(){
        return productRepository.getProductCoTheGiaoDichVaPrice();
    }
    public void giamUnit(long productId,int soLuong){
        productRepository.giamUnit(productId, soLuong);
    }
}

