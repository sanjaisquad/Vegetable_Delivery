package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.SuperAdmin;
import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.services.SuperAdminService;
import com.greengroc.v1api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/superAdmins")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/register")
    public ResponseEntity<SuperAdmin> registerSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        return ResponseEntity.ok(superAdminService.registerSuperAdmin(superAdmin));
    }

    @GetMapping("/shops/requests")
    public ResponseEntity<List<Shop>> getAllShopRequests() {return ResponseEntity.ok(shopService.getAllShopRequests());}
}
