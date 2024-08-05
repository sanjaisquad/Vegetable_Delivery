package com.greengroc.v1api.services;

import com.greengroc.v1api.models.*;
import com.greengroc.v1api.repositories.OrderRepository;
import com.greengroc.v1api.repositories.ShopRepository;
import com.greengroc.v1api.repositories.CustomerRepository;
import com.greengroc.v1api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Long shopId, Order orderData) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found"));

        Customer customer = customerRepository.findById(orderData.getCustomer().getID())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<OrderItem> orderItems = orderData.getOrderItems();
        for (OrderItem item : orderItems) {
            Product product = productRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setProduct(product);
            item.setOrder(orderData);
        }

        orderData.setShop(shop);
        orderData.setCustomer(customer);
        orderData.setOrderItems(orderItems);

        return orderRepository.save(orderData);
    }

    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerID(customerId);
    }

    public Order getFirstNonCompletedOrderByCustomerId(Long customerId) {
        return orderRepository.findFirstByCustomerIdAndStatusIn(customerId, List.of("Pending", "Delivery", "Cancel")).orElse(null);
    }

    public Order updateOrderStatus(Long orderId, String orderStatus) {
        Order order = getOrderDetails(orderId);
        order.setStatus(orderStatus);
        return orderRepository.save(order);
    }
}
