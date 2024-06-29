package com.greengroc.v1api.services;

import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Shop requestShopCreation(Shop shop) {
        shop.setApproved(false);
        System.out.println("shop requesting super admin");
        return shopRepository.save(shop);
    }

    public Shop approveShop(Shop shop) {
        shop.setApproved(true);
        return shopRepository.save(shop);
    }

    public Shop getShopDetails(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    public List<Shop> getAllShopRequests() {
        return shopRepository.findByIsApproved(false);
    }
}
