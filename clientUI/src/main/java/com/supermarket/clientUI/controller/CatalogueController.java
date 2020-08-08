package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.Category;
import com.supermarket.clientUI.proxies.CategoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CatalogueController {

    @Autowired
    private CategoryProxy categoryProxy;

    @GetMapping(value = "catalogue")
    public String index(Model model) {

        ArrayList<Category> categories = categoryProxy.allCategories();
        System.out.println(categories);

        model.addAttribute("categories", categories);

        return "catalogue";
    }
}
