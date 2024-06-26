package com.greengroc.v1api.repositorys;

import com.greengroc.v1api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface ProductRepository extends JpaRepository<Product,Integer> {

   Optional<Product> findByName(String name);
}
