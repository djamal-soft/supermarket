package com.supermarket.categories.controllers;

import com.supermarket.categories.dao.CategoryDao;
import com.supermarket.categories.exceptions.CategoryNotFoundException;
import com.supermarket.categories.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(value = "/categories")
    public List<Category> allCategories() {

        return categoryDao.findAll();
    }

    @GetMapping(value = "/categories/{id}")
    public Category getCategory(@PathVariable("id") int id) {

        Category category = categoryDao.findById(id);

        if(category == null) {
            throw new CategoryNotFoundException();
        }

        return category;
    }

    @PostMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody Category category) {

        categoryDao.save(category);
    }

    @PutMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(@RequestBody Category category) {

        categoryDao.save(category);
    }

    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") int id) {

        Category category = categoryDao.findById(id);

        if(category == null) {
            throw new CategoryNotFoundException();
        }

        categoryDao.delete(category);
    }
}
