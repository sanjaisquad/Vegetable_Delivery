package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopAdmin;
import com.greengroc.v1api.services.ShopAdminService;
import com.greengroc.v1api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopAdmins")
public class ShopAdminController {

    @Autowired
    private ShopAdminService shopAdminService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/register")
    public ResponseEntity<ShopAdmin> registerShopAdmin(@RequestBody ShopAdmin shopAdmin) {
        return ResponseEntity.ok(shopAdminService.registerShopAdmin(shopAdmin));
    }

    @PostMapping("/shops/request")
    public ResponseEntity<Shop> requestShopCreation(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.requestShopCreation(shop));
    }

    @PostMapping("/shops/approve")
    public ResponseEntity<Shop> approveShop(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.approveShop(shop));
    }
}
