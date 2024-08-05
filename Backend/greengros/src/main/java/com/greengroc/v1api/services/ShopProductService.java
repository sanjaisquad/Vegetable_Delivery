package com.greengroc.v1api.services;

import com.greengroc.v1api.models.Product;
import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopProduct;
import com.greengroc.v1api.repositories.ProductRepository;
import com.greengroc.v1api.repositories.ShopProductRepository;
import com.greengroc.v1api.repositories.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProductService {

    @Autowired
    private ShopProductRepository shopProductRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToShop(Long shopId, int productId, ShopProduct shopProduct) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalStateException("Shop not found with id: " + shopId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found with id: " + productId));

        shopProduct.setShop(shop);
        shopProduct.setProduct(product);
        System.out.println("im going to add product");
        shopProductRepository.save(shopProduct);
    }

    @Transactional
    public List<ShopProduct> updateShopProducts(Long shopId, List<ShopProduct> updatedShopProducts) {
        List<ShopProduct> existingShopProducts = shopProductRepository.findByShopId(shopId);

        for (ShopProduct updatedShopProduct : updatedShopProducts) {
            for (ShopProduct existingShopProduct : existingShopProducts) {
                if (existingShopProduct.getId().equals(updatedShopProduct.getId())) {
                    existingShopProduct.setStock(updatedShopProduct.getStock());
                    existingShopProduct.setPrice((long) updatedShopProduct.getPrice());
                    existingShopProduct.setAvilablity(updatedShopProduct.isAvilablity()); // Corrected method name
                }
            }
        }

        return shopProductRepository.saveAll(existingShopProducts);
    }


    public List<ShopProduct> getShopProducts(Long shopId) {
        return shopProductRepository.findByShopId(shopId);
    }
}
