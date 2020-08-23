package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.models.Order;
import com.supermarket.employeeUI.proxies.OrderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyOrdersController {

    @Autowired
    OrderProxy orderProxy;

    @GetMapping(value = "/my-orders")
    public String myOrders(Model model) {

        List<Order> orders = orderProxy.deliveryManOrders();
        model.addAttribute("orders", orders);

        return "pages/delivery/my-orders";
    }

    @PutMapping(value = "/next-status")
    public String orderToNextStatus(@RequestParam("id") int id) {

        orderProxy.orderNextStatus(id);

        return "redirect:/";
    }

    @PutMapping(value = "/refuse-order")
    public String refuseOrder(@RequestParam("id") int id) {

        orderProxy.refuseOrder(id);

        return "redirect:/";
    }
}
