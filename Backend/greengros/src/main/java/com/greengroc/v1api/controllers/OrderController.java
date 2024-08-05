package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Order;
import com.greengroc.v1api.services.OrderService;
import com.greengroc.v1api.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping(path = "{shopID}")
    public ResponseEntity<Order> createOrder(@PathVariable("shopID") Long shopId, @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(shopId, order);
        notificationService.notifyShop(createdOrder);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}/pending")
    public ResponseEntity<Order> getPendingOrderByCustomerId(@PathVariable Long customerId) {
        Order pendingOrder = orderService.getFirstNonCompletedOrderByCustomerId(customerId);
        if (pendingOrder != null) {
            return ResponseEntity.ok(pendingOrder);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, order.getStatus()));
    }

    @GetMapping("/shop/{shopId}/notifications")
    public Order getNextNotification(@PathVariable Long shopId) {
        Order nextNotification = notificationService.getNextNotification(shopId);
        if (nextNotification != null) {
            return nextNotification;
        }
        return null;
    }
}
