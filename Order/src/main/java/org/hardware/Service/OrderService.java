package org.hardware.Service;

import org.hardware.Entity.Order;
import org.hardware.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public void saveOrder(Order order) {
        order.setOrderDate(new Date());
        orderRepository.save(order);
    }

    public Optional<Order> findOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> findAllOrder() {
        List<Order> all = orderRepository.findAll();
        return all;

    }

}
