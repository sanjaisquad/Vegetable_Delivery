package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Customer;
import com.greengroc.v1api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControler {


        @Autowired
        private CustomerService customerService;

        @GetMapping("/{customerId}")
        public ResponseEntity<Customer> getCustomerDetails(@PathVariable Long customerId) {
            System.out.println("im in customer");
            return ResponseEntity.ok(customerService.getCustomerDetails(customerId));
        }

}
