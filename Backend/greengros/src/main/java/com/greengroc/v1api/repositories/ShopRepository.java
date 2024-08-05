package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByIsApproved(boolean isApproved);
    @Query("SELECT s FROM Shop s WHERE s.shopAdmin.id = :adminID")
    Shop findByShopAdminId(Long adminID);
    List<Shop> findByIsApprovedTrue();

}
