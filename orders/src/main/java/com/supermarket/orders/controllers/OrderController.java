package com.supermarket.orders.controllers;

import com.supermarket.orders.dao.OrderDao;
import com.supermarket.orders.dao.OrderDetailsDao;
import com.supermarket.orders.models.Order;
import com.supermarket.orders.models.OrderDetails;
import com.supermarket.orders.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderDao orderDao;

     @Autowired
     private OrderDetailsDao orderDetailsDao;

     @Autowired
     private ProductProxy productProxy;

    /**
     * Gey All Orders
     * @return
     */
    @GetMapping(value = "orders")
    public List<Order> index() {
        List<Order> orders = orderDao.findAll();
        // Get Orders Products from products service
        for (Order o : orders) {
            for(OrderDetails od: o.getOrderDetails()) {
                od.setProduct(productProxy.getProduct(od.getProductId()));
            }
        }
        return orders;
    }

    @GetMapping(value = "/orders/{id}")
    public Order getOrder(@PathVariable("id") int id) {
        Order order = orderDao.findById(id);

        for(OrderDetails details: order.getOrderDetails()) {
            details.setProduct(productProxy.getProduct(details.getProductId()));
        }

        return order;
    }

    /**
     * Store new order
     * @param order
     * @return
     */
    @PostMapping(value = "orders")
    public Order store(@RequestBody Order order) {

        order = orderDao.save(order);

        return storeOrderDetails(order, order.getOrderDetails());
    }

    private Order storeOrderDetails(Order order, List<OrderDetails> details) {

        Order orderCopy = new Order();
        orderCopy.setId(order.getId());
        orderCopy.setAddress(order.getAddress());
        orderCopy.setOrderDate(order.getOrderDate());
        orderCopy.setStatus(order.getStatus());

        for(OrderDetails od: details) {
            od.setOrder(orderCopy);
        }
        details = orderDetailsDao.saveAll(details);
        orderCopy.setOrderDetails(details);

        return orderCopy;
    }
}
