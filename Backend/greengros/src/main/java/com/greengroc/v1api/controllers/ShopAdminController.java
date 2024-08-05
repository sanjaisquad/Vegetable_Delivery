package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopAdmin;
import com.greengroc.v1api.services.ShopAdminService;
import com.greengroc.v1api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/shopAdmins")
public class ShopAdminController {

    @Autowired
    private ShopAdminService shopAdminService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/{adminId}/shop")
    public ResponseEntity<Shop> getAdminShop(@PathVariable("adminId") Long adminId){
        try {
            Shop adminShop = shopService.getshop(adminId);
            return ResponseEntity.ok(adminShop);
        } catch (IllegalAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ShopAdmin> registerShopAdmin(@RequestBody ShopAdmin shopAdmin) {
        return ResponseEntity.ok(shopAdminService.registerShopAdmin(shopAdmin));
    }

    @PostMapping("/shops/request")
    public ResponseEntity<Shop> requestShopCreation(
            @RequestParam Long shopAdminId,
            @RequestBody Shop shop) {
        try {
            Shop createdShop = shopService.requestShopCreation(shopAdminId, shop);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShop);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/shops/{shopId}/approve")
    public ResponseEntity<Shop> approveShop(@PathVariable("shopId") Long shopId) {
        try {
            Shop approvedShop = shopService.approveShop(shopId);
            return ResponseEntity.ok(approvedShop);
        } catch (IllegalAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
