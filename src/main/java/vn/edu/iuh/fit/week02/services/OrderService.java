package vn.edu.iuh.fit.week02.services;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.models.Orders;
import vn.edu.iuh.fit.week02.repository.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderService {
    private OrderRepository orderRepository;
    public OrderService(){
        orderRepository = new OrderRepository();
    }

    public void insert(Orders orders){
        orderRepository.insert(orders);
    }
    public void update(Orders orders){
        orderRepository.update(orders);
    }
    public List<Orders> getAll(){
        return orderRepository.getAll();
    }

    public Optional<Orders> findByID(long id) {
        return orderRepository.findByID(id);
    }
    public Map<Orders, OrderDetail> findByIDDetail(long id){return orderRepository.findByIDDetail(id);}
    public Optional<Orders> kiemTraTonTai(Orders orders){
        return orderRepository.kiemTraTonTai(orders);

    }
}

