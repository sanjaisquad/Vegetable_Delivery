package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerID(Long customerID);
    Optional<Order> findFirstByCustomerIdAndStatusIn(Long customerId, List<String> statuses);
}
