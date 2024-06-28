package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByIsApproved(boolean isApproved);
}
