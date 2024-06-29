package com.greengroc.v1api.services;

import com.greengroc.v1api.models.ShopProduct;
import com.greengroc.v1api.repositories.ShopProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopProductService {

    @Autowired
    private ShopProductRepository shopProductRepository;

    public ShopProduct addProductToShop(Long shopId, ShopProduct shopProduct) {
        shopProduct.setId(shopId);
        return shopProductRepository.save(shopProduct);
    }
}
