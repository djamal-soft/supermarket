package com.supermarket.orders.dao;

import com.supermarket.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findAll();
    Order findById(int id);
}
