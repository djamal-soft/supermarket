package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.Category;
import com.supermarket.clientUI.models.Product;
import com.supermarket.clientUI.proxies.CategoryProxy;
import com.supermarket.clientUI.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private ProductProxy productProxy;

    @Autowired
    private CategoryProxy categoryProxy;

    @GetMapping(value = "/")
    public String index(Model model) {

        ArrayList<Product> products = productProxy.allProducts();
        ArrayList<Category> categories = categoryProxy.allCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "index";
    }
}
