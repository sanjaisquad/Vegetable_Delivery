package com.greengroc.v1api.models;

import com.greengroc.v1api.utils.UserType;
import jakarta.persistence.Entity;

@Entity
public class Customer extends User{


    private int loyaltyPoints;

    //Constructor

    public Customer(Long ID, String name, String email, String password, String address, String mobileNumber, UserType userType, int loyaltyPoints) {
        super(ID, name, email, password, address, mobileNumber, userType);
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(String name, String email, String password, String address, String mobileNumber, UserType userType, int loyaltyPoints) {
        super(name, email, password, address, mobileNumber, userType);
        this.loyaltyPoints = loyaltyPoints;
    }
    public Customer(User user){
        super(user);
        this.loyaltyPoints=0;
    }

    public Customer(int loyaltyPoints) {

        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer() {
    }

    //getter and setter


    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
