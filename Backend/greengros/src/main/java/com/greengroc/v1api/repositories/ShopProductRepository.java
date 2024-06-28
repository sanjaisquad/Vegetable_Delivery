package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Long> {
}
