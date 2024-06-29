package com.greengroc.v1api.services;

import com.greengroc.v1api.models.ShopAdmin;
import com.greengroc.v1api.repositories.ShopAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAdminService {

    @Autowired
    private ShopAdminRepository shopAdminRepository;

    public ShopAdmin registerShopAdmin(ShopAdmin shopAdmin) {
        return shopAdminRepository.save(shopAdmin);
    }
}
