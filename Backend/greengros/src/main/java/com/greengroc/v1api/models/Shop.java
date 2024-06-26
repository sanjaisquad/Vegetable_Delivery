package com.greengroc.v1api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private boolean isApproved;

    @OneToOne
    @JoinColumn(name = "shop_admin_id")
    private ShopAdmin shopAdmin;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryPartner> deliveryPartners;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopProduct> shopProducts;

    //constructor


    public Shop(Long id, String name, String address, boolean isApproved, ShopAdmin shopAdmin, List<DeliveryPartner> deliveryPartners, List<ShopProduct> shopProducts) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isApproved = isApproved;
        this.shopAdmin = shopAdmin;
        this.deliveryPartners = deliveryPartners;
        this.shopProducts = shopProducts;
    }

    public Shop(String name, String address, boolean isApproved, ShopAdmin shopAdmin, List<DeliveryPartner> deliveryPartners, List<ShopProduct> shopProducts) {
        this.name = name;
        this.address = address;
        this.isApproved = isApproved;
        this.shopAdmin = shopAdmin;
        this.deliveryPartners = deliveryPartners;
        this.shopProducts = shopProducts;
    }

    public Shop() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public ShopAdmin getShopAdmin() {
        return shopAdmin;
    }

    public void setShopAdmin(ShopAdmin shopAdmin) {
        this.shopAdmin = shopAdmin;
    }

    public List<DeliveryPartner> getDeliveryPartners() {
        return deliveryPartners;
    }

    public void setDeliveryPartners(List<DeliveryPartner> deliveryPartners) {
        this.deliveryPartners = deliveryPartners;
    }

    public List<ShopProduct> getShopProducts() {
        return shopProducts;
    }

    public void setShopProducts(List<ShopProduct> shopProducts) {
        this.shopProducts = shopProducts;
    }
}
