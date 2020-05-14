package com.supermarket.products.controllers;


import com.supermarket.products.dao.CategoryDao;
import com.supermarket.products.dao.ProductDao;
import com.supermarket.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

//    @Autowired
//    private CategoryDao categoryDao;

    @GetMapping(value = "products")
    public List<Product> allProducts() {

        return productDao.findAll();
    }

    @GetMapping(value = "products/{id}")
    public Product show(@PathVariable("id") int id) {

        return productDao.findById(id);
    }

    @PostMapping(value = "products/search")
    public List<Product> getProductsByIDs(@RequestBody List<Integer> ids) {

        System.out.println(ids);
        List<Product> products = productDao.findAll();
        List<Product> filtredProducts = new ArrayList<>();

        for(Product p : products) {
            if(ids.indexOf(p.getId()) >= 0) {
                filtredProducts.add(p);
            }
        }

        return filtredProducts;
    }

    @PostMapping(value = "products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product store(@RequestBody Product product) {

         return productDao.save(product);
    }

    @PutMapping(value = "products")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody Product product) {

        productDao.save(product);
    }

    @DeleteMapping(value = "products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") int id) {
        productDao.deleteById(id);
    }
}
