package com.supermarket.orders.controllers;

import com.supermarket.orders.dao.OrderDao;
import com.supermarket.orders.models.Order;
import com.supermarket.orders.models.OrderDetails;
import com.supermarket.orders.proxies.ClientProxy;
import com.supermarket.orders.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryManOrdersController {

    @Autowired
    OrderDao orderDao;
    @Autowired
    private ProductProxy productProxy;

    @Autowired
    private ClientProxy clientProxy;


    @GetMapping(value = "/delivery-man-orders/{id}")
    public List<Order> deliveryManOrders(@PathVariable("id") int id) {

        List<Order> orders = orderDao.findAllByDeliveryManId(id);
        // Get Orders Products and clients
        for (Order o : orders) {
            o.setClient(clientProxy.getClientById(o.getClientId()));
            for(OrderDetails od: o.getOrderDetails()) {
                od.setProduct(productProxy.getProduct(od.getProductId()));
            }
        }

        return orders;
    }
}
