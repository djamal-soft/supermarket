package com.supermarket.clientUI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.clientUI.auth.ApplicationUser;
import com.supermarket.clientUI.models.CartItem;
import com.supermarket.clientUI.models.Order;
import com.supermarket.clientUI.models.OrderDetails;
import com.supermarket.clientUI.proxies.OrderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private static final String SESSION_NAME = "CART";

    @Autowired
    OrderProxy orderProxy;

    @PostMapping(value = "/cart/send")
    public String sendOrder(HttpServletRequest request) {

        Order order = prepareRequest(request);
        System.out.println(order);

        try {
            orderProxy.sendOrder(order);
            getShoppingCart(request).clear();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }

        return "redirect:/cart";
    }



    /**
     * prepare request from session (shopping cart)
     * @return List<Order>
     */
    private Order prepareRequest(HttpServletRequest request) {
        Map<Integer, CartItem> cart = getShoppingCart(request);
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        Order order = new Order();
        OrderDetails details;
        order.setClientId(getClientId());
        order.setAddress(getClientAddress());
        for (CartItem item: cart.values()) {
            details = new OrderDetails();
            details.setProductId(item.getProduct().getId());
            details.setQuantity(item.getQuantity());

            orderDetailsList.add(details);
        }

        order.setOrderDetails(orderDetailsList);

        return order;
    }

    /**
     * Get Shopping Cart From Session and create it if it's not exist
     * @param request
     * @return Map
     */
    private Map<Integer, CartItem> getShoppingCart(HttpServletRequest request) {

        Map<Integer, CartItem> cart;
        cart = (Map<Integer, CartItem>) request.getSession().getAttribute(SESSION_NAME);

        // check cart is created
        if(cart == null) {
            cart = new HashMap<>();
            request.getSession().setAttribute(SESSION_NAME, cart);
        }

        return cart;
    }

    /**
     * Get client id from authentication
     * @return int
     */
    private int getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser client = (ApplicationUser)authentication.getPrincipal();
        return client.getId();
    }

    /**
     * Get client address from authentication
     * @return String
     */
    private String getClientAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser client = (ApplicationUser)authentication.getPrincipal();
        return client.getAddress();
    }
}
