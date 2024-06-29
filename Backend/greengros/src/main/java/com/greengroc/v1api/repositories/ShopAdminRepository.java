package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
}
