package com.greengroc.v1api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greengroc.v1api.utils.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ShopAdmin extends User{
    @OneToOne(mappedBy = "shopAdmin")
    @JsonBackReference
    private Shop shop;

    //constructor


    public ShopAdmin(Shop shop) {
        this.shop = shop;
    }

    public ShopAdmin(String name, String email, String password, String address, String mobileNumber, UserType userType, Shop shop) {
        super(name, email, password, address, mobileNumber, userType);
        this.shop = shop;
    }

    public ShopAdmin(Long ID, String name, String email, String password, String address, String mobileNumber, UserType userType, Shop shop) {
        super(ID, name, email, password, address, mobileNumber, userType);
        this.shop = shop;
    }
    public ShopAdmin(User user){
        super(user);
    }

    public ShopAdmin() {

    }

    //getter and setter
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
