package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.CartItem;
import com.supermarket.clientUI.models.Order;
import com.supermarket.clientUI.models.OrderDetails;
import com.supermarket.clientUI.models.Product;
import com.supermarket.clientUI.proxies.OrderProxy;
import com.supermarket.clientUI.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {

    private static final String SESSION_NAME = "CART";

    @Autowired
    private ProductProxy productProxy;

    @GetMapping(value = "/cart")
    public String index(Model model, HttpServletRequest request) {
        Map<Integer, CartItem> cart = getShoppingCart(request);
        ArrayList<CartItem> cartItems = new ArrayList<>(cart.values());
        model.addAttribute("cartItems", cartItems);
        return "shoppingCart";
    }

    @PostMapping(value = "/cart")
    public String addProduct(@RequestParam("id") int id, HttpServletRequest request) {

        addProductToCart(id, request);

        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * decrease quantity of Product
     * @param id
     * @param request
     * @return String
     */
    @PutMapping(value = "/cart")
    public String decreaseProduct(@RequestParam("id") int id, HttpServletRequest request) {

        Map<Integer, CartItem> cart = getShoppingCart(request);

        if(cart.get(id).getQuantity() > 1) {
            cart.get(id).decreaseQuantity();
        }

        return "redirect:/cart";
    }

    /**
     * Remove product from shopping cart
     * @param id
     * @param request
     * @return String
     */
    @DeleteMapping(value = "/cart")
    public String removeProduct(@RequestParam("id") int id, HttpServletRequest request) {

        Map<Integer, CartItem> cart = getShoppingCart(request);
        // key is product is
        cart.remove(id);

        return "redirect:/cart";
    }

    private void addProductToCart(int id, HttpServletRequest request) {
        Map<Integer, CartItem> cart = getShoppingCart(request);

        // check if product is exist in shopping cart
        if(cart.containsKey(id)) {
            // if exist increase quantity
            cart.get(id).increaseQuantity();
        }
        else {
            // else on create new cart item and add him to cart products
            cart.put(id, createCartItem(id));
        }

    }



    /**
     * Create new Cart Item
     * @param id
     * @return CartItem
     */
    private CartItem createCartItem(int id) {

        CartItem cartItem = new CartItem();
        Product product = productProxy.productById(id);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);

        return cartItem;
    }

    /**
     * Get Shopping Cart From Session and create it if it's not exist
     * @param request
     * @return
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
}
