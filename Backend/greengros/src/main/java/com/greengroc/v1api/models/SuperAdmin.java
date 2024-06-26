package com.greengroc.v1api.models;

import com.greengroc.v1api.utils.UserType;
import jakarta.persistence.Entity;

@Entity
public class SuperAdmin extends User{
    private String adminPrivileges;

    //constructor
    public SuperAdmin(int ID, String name, String email, String password, String address, String mobileNumber, UserType userType, String adminPrivileges) {
        super(ID, name, email, password, address, mobileNumber, userType);
        this.adminPrivileges = adminPrivileges;
    }

    public SuperAdmin(String name, String email, String password, String address, String mobileNumber, UserType userType, String adminPrivileges) {
        super(name, email, password, address, mobileNumber, userType);
        this.adminPrivileges = adminPrivileges;
    }

    public SuperAdmin(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }

    public SuperAdmin(){

    }

    //Getter and setter

    public String getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }
}
