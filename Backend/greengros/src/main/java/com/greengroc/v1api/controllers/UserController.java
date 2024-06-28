package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.User;
import com.greengroc.v1api.models.userLoginRequest;
import com.greengroc.v1api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody userLoginRequest user) {
        return ResponseEntity.ok(userService.loginUser(user.getEmail(), user.getPassword()));
    }
}
