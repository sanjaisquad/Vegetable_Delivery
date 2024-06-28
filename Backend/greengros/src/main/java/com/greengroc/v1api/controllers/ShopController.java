package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopProduct;
import com.greengroc.v1api.services.ShopService;
import com.greengroc.v1api.services.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopProductService shopProductService;

    @GetMapping("/{shopId}")
    public ResponseEntity<Shop> getShopDetails(@PathVariable Long shopId) {
        return ResponseEntity.ok(shopService.getShopDetails(shopId));
    }

    @PostMapping("/{shopId}/products")
    public ResponseEntity<ShopProduct> addProductToShop(@PathVariable Long shopId, @RequestBody ShopProduct shopProduct) {
        return ResponseEntity.ok(shopProductService.addProductToShop(shopId, shopProduct));
    }
}
