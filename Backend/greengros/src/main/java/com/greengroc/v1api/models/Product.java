package com.greengroc.v1api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="product")
@Table(name="product",
       uniqueConstraints = {
        @UniqueConstraint(name="product_name_unique",columnNames="Name")
       })
public class Product {
    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",

            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private int ID;
    @Column(
            name="Name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name="price",
            nullable = false
    )
    private Double Price;
    @Column(
            name="catagori",
            nullable = false
    )
    private String Catagori;

    @Column(
            name="image",
            nullable = false
    )
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopProduct> shopProducts;

    public Product(int ID, String name, Double price, String catagori, String image) {
        this.ID = ID;
        this.name = name;
        Price = price;
        Catagori = catagori;
        this.image = image;
    }

    public Product(String name, Double price, String catagori, String image) {
        this.name = name;
        Price = price;
        Catagori = catagori;
        this.image = image;
    }

    public Product(){

    }

    public String getImage() {return image;
    }

    public void setImage(String image) {this.image = image;
    }

    public String getCatagori() {
        return Catagori;
    }

    public void setCatagori(String catagori) {
        Catagori = catagori;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }
}
