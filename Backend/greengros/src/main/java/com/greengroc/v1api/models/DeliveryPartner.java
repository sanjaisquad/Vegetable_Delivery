package com.greengroc.v1api.models;

import com.greengroc.v1api.utils.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class DeliveryPartner extends User {
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    //Constructor

    public DeliveryPartner(Long ID, String name, String email, String password, String address, String mobileNumber, UserType userType, boolean isApproved, Shop shop) {
        super(ID, name, email, password, address, mobileNumber, userType);
        this.isApproved = isApproved;
        this.shop = shop;
    }

    public DeliveryPartner(String name, String email, String password, String address, String mobileNumber, UserType userType, boolean isApproved, Shop shop) {
        super(name, email, password, address, mobileNumber, userType);
        this.isApproved = isApproved;
        this.shop = shop;
    }

    public DeliveryPartner(boolean isApproved, Shop shop) {

        this.isApproved = isApproved;
        this.shop = shop;
    }
    public DeliveryPartner(){


    }

    //getter and setter

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
