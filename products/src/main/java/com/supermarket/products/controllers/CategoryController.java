package com.supermarket.products.controllers;

import com.supermarket.products.dao.CategoryDao;
import com.supermarket.products.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(value = "/categories")
    public List<Category> categories() {

        return categoryDao.findAll();
    }

    @GetMapping(value = "/categories/{id}")
    public Category show(@PathVariable("id") int id) {

        return categoryDao.findById(id);
    }

    @PostMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Category category) {

        categoryDao.save(category);
    }

    @PutMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody Category category) {

        categoryDao.save(category);
    }

    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {

        Category category = categoryDao.findById(id);
        categoryDao.delete(category);
    }
}
