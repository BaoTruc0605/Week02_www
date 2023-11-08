package vn.edu.iuh.fit.week02.services;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.models.Orders;
import vn.edu.iuh.fit.week02.repository.EmployeeRepository;
import vn.edu.iuh.fit.week02.repository.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

public class OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    public OrderDetailService(){
        orderDetailRepository = new OrderDetailRepository();
    }

    public void insert(OrderDetail orderDetail){
        orderDetailRepository.insert(orderDetail);
    }
    public void update(OrderDetail orderDetail){
        orderDetailRepository.update(orderDetail);
    }
    public List<OrderDetail> getAll(){
        return orderDetailRepository.getAll();
    }

    public Optional<OrderDetail> findByID(long productId, long orderId) {
        return orderDetailRepository.findByID(productId,orderId);
    }
    public Optional<OrderDetail> kiemTraTonTai(OrderDetail orderDetail){
        return orderDetailRepository.kiemTraTonTai(orderDetail);
    }
    public void tangSoLuong(long productId,long orderId, int soLuong){
        orderDetailRepository.tangSoLuong(productId,orderId, soLuong);
    }
}

