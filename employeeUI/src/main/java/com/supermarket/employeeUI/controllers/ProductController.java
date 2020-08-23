package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.models.Category;
import com.supermarket.employeeUI.models.Product;
import com.supermarket.employeeUI.proxies.CategoryProxy;
import com.supermarket.employeeUI.proxies.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductProxy productProxy;

    @Autowired
    CategoryProxy categoryProxy;

    @GetMapping(value = "products")
    public String allProductView(Model model) {
        List<Product> products = productProxy.allProducts();
        model.addAttribute("products", products);

        return "pages/admin/products/products";
    }

    @GetMapping(value = "products/add")
    public String addProductView(Model model) {

        List<Category> categories = categoryProxy.allCategories();
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "pages/admin/products/add-product";
    }

    @GetMapping(value = "products/update/{id}")
    public String updateProductView(@PathVariable("id") int id, Model model) {
        List<Category> categories = categoryProxy.allCategories();
        Product product = productProxy.getProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "/pages/admin/products/add-product";
    }

    @PostMapping(value = "products")
    public String addProduct(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("categories")String categories,
            @RequestParam("image") MultipartFile multipartFile) {
        String imageUrl = "fdgsdfgsdfg";

        try {
            System.out.println(multipartFile.getBytes());
            imageUrl = productProxy.uploadImage(multipartFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCategories(categories);
        product.setImage(imageUrl);
        System.out.println(product);
        productProxy.addProduct(product);

        return "redirect:/products";
    }

    @DeleteMapping(value = "products")
    public String deleteProduct(@RequestParam("id") int id) {

        productProxy.deleteProduct(id);

        return "redirect:/";
    }

}
