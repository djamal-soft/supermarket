package com.supermarket.orders.dao;

import com.supermarket.orders.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer> {
}
