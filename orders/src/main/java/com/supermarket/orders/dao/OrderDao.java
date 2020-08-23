package com.supermarket.orders.dao;

import com.supermarket.orders.models.DeliveryCount;
import com.supermarket.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findAll();
    List<Order> findAllByClientId(int id);
    List<Order> findAllByDeliveryManId(int id);
    Order findById(int id);
    void deleteById(int id);

    @Query("SELECT o.deliveryManId FROM Order AS o GROUP BY o.deliveryManId ORDER BY COUNT(o.deliveryManId) ASC")
//@Query("SELECT new com.supermarket.orders.models.DeliveryCount(o.deliveryManId, COUNT(o.deliveryManId)) "
//        + "FROM Order AS o GROUP BY o.deliveryManId ORDER BY o.id DESC")
    List<Integer> countTotalOrdersByDeliveryMan();
}
