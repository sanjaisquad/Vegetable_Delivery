package com.greengroc.v1api.services;

import com.greengroc.v1api.Exceptions.ShopNotFoundException;
import com.greengroc.v1api.models.Shop;
import com.greengroc.v1api.models.ShopAdmin;
import com.greengroc.v1api.repositories.ShopAdminRepository;
import com.greengroc.v1api.repositories.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final ShopAdminRepository shopAdminRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository, ShopAdminRepository shopAdminRepository) {
        this.shopRepository = shopRepository;
        this.shopAdminRepository = shopAdminRepository;
    }

    public Shop requestShopCreation(Long shopAdminId, Shop shop) throws Exception {
        ShopAdmin shopAdmin = shopAdminRepository.findById(shopAdminId)
                .orElseThrow(() -> new Exception("ShopAdmin not found with id: " + shopAdminId));

        shop.setShopAdmin(shopAdmin);
        shop.setApproved(false);

        return shopRepository.save(shop);
    }

    @Transactional
    public Shop approveShop(Long shopId) {
        Shop myShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException("Shop with id " + shopId + " does not exist"));

        myShop.setApproved(true);
        return shopRepository.save(myShop);
    }

    public List<Shop> getShopDetails() {
        List<Shop> approvedShops = shopRepository.findByIsApprovedTrue();
        if (approvedShops.isEmpty()) {
            throw new ShopNotFoundException("No approved shops found.");
        }
        return approvedShops;
    }

    public List<Shop> getAllShopRequests() {
        return shopRepository.findByIsApproved(false);
    }

    public Shop getshop(Long adminId) {
        Shop AdminShop = null;
        try {
            AdminShop = shopRepository.findByShopAdminId(adminId);
            //return AdminShop;
        } catch (IllegalStateException e) {
            System.out.println("not found");
        }
        return AdminShop;
    }
//                .orElseThrow(() -> new ShopNotFoundException("Shop not found with id: " + adminId));    }
}
