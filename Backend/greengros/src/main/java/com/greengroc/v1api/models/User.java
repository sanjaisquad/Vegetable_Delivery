package com.greengroc.v1api.models;

import jakarta.persistence.*;
import com.greengroc.v1api.utils.UserType;

@Entity(name="user")
@Table(name="user",
        uniqueConstraints = {
                @UniqueConstraint(name="user_email_unique",columnNames="Email")
        })
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",

            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long ID;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "Email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "mobileNumber",
            nullable = false
    )
    private String mobileNumber;

    @Column(name="user_type",
    nullable = false)
//    @Enumerated(EnumType.STRING)
    private UserType userType;

    //Constructor
    public User(Long ID, String name, String email, String password, String address, String mobileNumber, UserType userType) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.userType = userType;
    }

    public User(String name, String email, String password, String address, String mobileNumber, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.userType = userType;
    }

    public User(User user) {
        this.name=user.name;
        this.email = user.email;
        this.password = user.password;
        this.address = user.address;
        this.mobileNumber = user.mobileNumber;
        this.userType = user.userType;

    }
    public User(){}

    //getter and setter
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Long id;
//private String name;
//private String email;
//private String password;
//private String address;
//private String mobileNumber;


