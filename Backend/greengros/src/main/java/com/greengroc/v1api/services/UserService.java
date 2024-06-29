package com.greengroc.v1api.services;


import com.greengroc.v1api.models.*;
import com.greengroc.v1api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.greengroc.v1api.utils.UserType.CUSTOMER;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopAdminRepository shopAdminRepository;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    // Method to register a new user
    public User registerUser(User request) {
        if (isEmailAlreadyRegistered(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        User user = createUserBasedOnType(request);
        return userRepository.save(user);
    }

    // Method to handle user login
    public User loginUser(String email,String password) {
        if (!isEmailAlreadyRegistered(email)) {
            throw new RuntimeException("Email not avilable");
        }  Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("password not correct");
        }
    }

    // Create user based on user type
    private User createUserBasedOnType(User request) {
        switch (request.getUserType()) {
            case CUSTOMER:
                Customer customer = new Customer();
                populateCommonUserData(customer, request);
                // Additional customer-specific logic, if any
                return customerRepository.save(customer);
            case SHOP_ADMIN:
                ShopAdmin shopAdmin = new ShopAdmin();
                populateCommonUserData(shopAdmin, request);
                // Additional shop admin-specific logic, if any
                return shopAdminRepository.save(shopAdmin);
            case SUPER_ADMIN:
                SuperAdmin superAdmin = new SuperAdmin();
                populateCommonUserData(superAdmin, request);
                // Additional super admin-specific logic, if any
                return superAdminRepository.save(superAdmin);
            case DELIVERY_PARTNER:
                DeliveryPartner deliveryPartner = new DeliveryPartner();
                populateCommonUserData(deliveryPartner, request);
                // Additional delivery partner-specific logic, if any
                return deliveryPartnerRepository.save(deliveryPartner);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    // Populate common user data
    private void populateCommonUserData(User user, User request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setMobileNumber(request.getMobileNumber());
        user.setAddress(request.getAddress());
        user.setUserType(request.getUserType());
    }

    // Method to check if the email is already registered
    private boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Other methods (getUserById, updateUser, deleteUser) remain unchanged
}
