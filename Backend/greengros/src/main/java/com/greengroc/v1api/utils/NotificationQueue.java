package com.greengroc.v1api.utils;

import com.greengroc.v1api.models.Order;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class NotificationQueue {
    private static Map<Long, Queue<Order>> notificationMap = new HashMap<>();

    public static void addNotification(Long shopId, Order order) {
        notificationMap.computeIfAbsent(shopId, k -> new LinkedList<>()).offer(order);
    }

    public static Order getNextNotification(Long shopId) {
        Queue<Order> queue = notificationMap.get(shopId);

        if (queue != null) {
            return queue.poll();
        }
        return null;
    }

    public static boolean hasNotifications(Long shopId) {
        Queue<Order> queue = notificationMap.get(shopId);
        return queue != null && !queue.isEmpty();
    }
}
