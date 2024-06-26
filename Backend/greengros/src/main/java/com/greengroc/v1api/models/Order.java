package com.greengroc.v1api.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String status;
        private Date orderDate;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @ManyToOne
        @JoinColumn(name = "shop_id")
        private Shop shop;

        @ManyToOne
        @JoinColumn(name = "delivery_partner_id")
        private DeliveryPartner deliveryPartner;

        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<OrderItem> orderItems = new ArrayList<>();

        // Constructor, getters, and setters

        public Order() {
        }

        public Order(String status, Date orderDate, Customer customer, Shop shop, DeliveryPartner deliveryPartner) {
            this.status = status;
            this.orderDate = orderDate;
            this.customer = customer;
            this.shop = shop;
            this.deliveryPartner = deliveryPartner;
        }

        public void addOrderItem(OrderItem orderItem) {
            orderItems.add(orderItem);
            orderItem.setOrder(this);
        }

        public void removeOrderItem(OrderItem orderItem) {
            orderItems.remove(orderItem);
            orderItem.setOrder(null);
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Shop getShop() {
            return shop;
        }

        public void setShop(Shop shop) {
            this.shop = shop;
        }

        public DeliveryPartner getDeliveryPartner() {
            return deliveryPartner;
        }

        public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
            this.deliveryPartner = deliveryPartner;
        }

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }


}
