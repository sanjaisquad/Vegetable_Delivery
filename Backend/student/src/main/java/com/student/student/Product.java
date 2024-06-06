package com.student.student;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name="product")
@Table(name="product",
       uniqueConstraints = {
        @UniqueConstraint(name="product_email_unique",columnNames="Emaill")
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
            name="Emaill",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name="price",
            nullable = false
    )
    private double Price;
    @Column(
            name="catagori",
            nullable = false
    )
    private String Catagori;

    @Column(
            name="avilable",
            nullable = false
    )
    private String Avilable;


    public Product(int ID, String name, double price, String catagori, String avilable) {
        this.ID = ID;
        this.email = name;
        this.Price = price;
        this.Catagori = catagori;
        this.Avilable = avilable;
    }

    public Product(String name, double price, String catagori, String avilable) {
        this.email = name;
        this.Price = price;
        this.Catagori = catagori;
        this.Avilable = avilable;
    }

    public String getAvilable() {
        return Avilable;
    }

    public void setAvilable(String avilable) {
        Avilable = avilable;
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
        return email;
    }

    public void setName(String name) {
        email = name;
    }
}
