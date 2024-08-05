package com.greengroc.v1api.services;

import com.greengroc.v1api.models.ShopAdmin;
import com.greengroc.v1api.repositories.ShopAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAdminService {

    private final ShopAdminRepository shopAdminRepository;

    @Autowired
    public ShopAdminService(ShopAdminRepository shopAdminRepository) {
        this.shopAdminRepository = shopAdminRepository;
    }

    public ShopAdmin registerShopAdmin(ShopAdmin shopAdmin) {
        return shopAdminRepository.save(shopAdmin);
    }
}
