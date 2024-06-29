package com.greengroc.v1api.services;

import com.greengroc.v1api.models.Order;
import com.greengroc.v1api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, String orderStatus) {
        Order order = getOrderDetails(orderId);
        order.setStatus(orderStatus);
        return orderRepository.save(order);
    }
}
