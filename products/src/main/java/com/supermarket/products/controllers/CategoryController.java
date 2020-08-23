package com.supermarket.products.controllers;



import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

//    @Autowired
//    private CategoryDao categoryDao;
//
//    @GetMapping(value = "/categories")
//    public List<ProductCategories> categories() {
//
//        return categoryDao.findAll();
//    }
//
//    @GetMapping(value = "/categories/{id}")
//    public ProductCategories show(@PathVariable("id") int id) {
//
//        return categoryDao.findById(id);
//    }
//
//    @PostMapping(value = "/categories")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void store(@RequestBody ProductCategories category) {
//
//        categoryDao.save(category);
//    }
//
//    @PutMapping(value = "/categories")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void update(@RequestBody ProductCategories category) {
//
//        categoryDao.save(category);
//    }
//
//    @DeleteMapping(value = "/categories/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("id") int id) {
//
//        ProductCategories category = categoryDao.findById(id);
//        categoryDao.delete(category);
//    }
}
