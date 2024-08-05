package com.greengroc.v1api.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
 //   @JsonManagedReference
    @JsonIgnore // Ignore this property during JSON serialization to avoid cyclic reference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
   // @JsonBackReference // Prevent serialization from this side to break cyclic reference
    private Product product;

    private boolean avilablity;
    private int stock;
    private float pricee;
    @JsonProperty("qty")
    private float qty;


// Constructors, getters, and setters


    public ShopProduct(Long id, Shop shop, Product product, boolean avilablity, int stock,float pricee,float qty) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.avilablity = avilablity;
        this.stock = stock;
        this.pricee=pricee;
        this.qty=qty;
    }

    public ShopProduct(Shop shop, Product product, boolean avilablity, int stock, float price,float qty) {
        this.shop = shop;
        this.product = product;
        this.avilablity = avilablity;
        this.stock = stock;
        this.pricee=price;
        this.qty=qty;
    }
    public ShopProduct() {
    }
    public float getPrice() {return pricee;}

    public void setPrice(Long price) {this.pricee = price;}

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

    public float getQuantity() {return qty;}

    public void setQuantity(float qty) {this.qty = qty;}
}
