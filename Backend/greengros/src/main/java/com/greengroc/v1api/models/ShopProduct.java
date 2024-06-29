package com.greengroc.v1api.models;

import jakarta.persistence.*;

@Entity
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private boolean avilablity;
    private int stock;

    // Constructors, getters, and setters


    public ShopProduct(Long id, Shop shop, Product product, boolean avilablity, int stock) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.avilablity = avilablity;
        this.stock = stock;
    }

    public ShopProduct(Shop shop, Product product, boolean avilablity, int stock) {
        this.shop = shop;
        this.product = product;
        this.avilablity = avilablity;
        this.stock = stock;
    }

    public ShopProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isAvilablity() {
        return avilablity;
    }

    public void setAvilablity(boolean avilablity) {
        this.avilablity = avilablity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
