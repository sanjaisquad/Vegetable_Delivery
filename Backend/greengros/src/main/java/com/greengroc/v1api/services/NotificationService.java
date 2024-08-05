package com.greengroc.v1api.services;

import com.greengroc.v1api.models.Order;
import com.greengroc.v1api.utils.NotificationQueue;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyShop(Order order) {
        Long shopId = order.getShopId();
        NotificationQueue.addNotification(shopId, order);
        // Placeholder for additional notification logic (email, SMS, etc.)
        System.out.println("Notifying shop " + shopId + " about order " + order.getId());
    }

    public boolean checkNotifications(Long shopId) {
        return NotificationQueue.hasNotifications(shopId);
    }

    public Order getNextNotification(Long shopId) {
        return NotificationQueue.getNextNotification(shopId);
    }
}
