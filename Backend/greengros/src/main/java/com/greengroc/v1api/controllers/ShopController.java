package com.greengroc.v1api.controllers;

import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopProduct;
import com.greengroc.v1api.services.ShopProductService;
import com.greengroc.v1api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopProductService shopProductService;

    @GetMapping("/{shopId}")
    public ResponseEntity<Shop> getShop(@PathVariable Long shopId) {
        try {
            Shop shop = shopService.getshop(shopId);
            return ResponseEntity.ok(shop);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        try {
            List<Shop> shop = shopService.getShopDetails();
            return ResponseEntity.ok(shop);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{shopId}/products")
    public ResponseEntity<List<ShopProduct>> getShopProducts(@PathVariable Long shopId) {
        try {
            List<ShopProduct> products = shopProductService.getShopProducts(shopId);
            return ResponseEntity.ok(products);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProductToShop(
            @RequestParam(required = true) Long shopId,
            @RequestParam(required = true) int productId,
            @RequestBody ShopProduct shopProduct) {
        try {
            shopProductService.addProductToShop(shopId, productId, shopProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to shop successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{shopId}/products")
    public ResponseEntity<List<ShopProduct>> updateShopProducts(
            @PathVariable Long shopId,
            @RequestBody List<ShopProduct> shopProducts) {
        try {
            List<ShopProduct> updatedProducts = shopProductService.updateShopProducts(shopId, shopProducts);
            return ResponseEntity.ok(updatedProducts);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/request")
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
}
