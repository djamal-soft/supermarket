package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.Product;
import com.supermarket.clientUI.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ProductController {

    @Autowired
    private ProductProxy productProxy;

    @GetMapping(value = "/catalogue/{id}")
    public String index(@PathVariable("id") String id, Model model) {
        ArrayList<Product> products = productProxy.productsByCategory(id);
        System.out.println(products);
        model.addAttribute("products", products);
        return "categoryProducts";
    }

//    @GetMapping(value = "")
//    public String show() {
//
//        return "";
//    }
}
