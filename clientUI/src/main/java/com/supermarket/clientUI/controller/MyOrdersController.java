package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.Order;
import com.supermarket.clientUI.proxies.OrderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyOrdersController {

    @Autowired
    OrderProxy orderProxy;

    @GetMapping(value = "my-orders")
    public String clientOrders(Model model) {
        List<Order> orders = orderProxy.clientOrders();
        model.addAttribute("orders", orders);
        return "my-orders";
    }

    @DeleteMapping(value = "my-orders")
    public String deleteOrder(@RequestParam int id) {

        orderProxy.deleteOrder(id);

        return "redirect:/";
    }
}
