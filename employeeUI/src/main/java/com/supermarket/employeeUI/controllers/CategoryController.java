package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.models.Category;
import com.supermarket.employeeUI.proxies.CategoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryProxy categoryProxy;

    @GetMapping(value = "categories")
    public String categoriesView(Model model) {
        List<Category> categories = categoryProxy.allCategories();
        model.addAttribute("categories", categories);

        return "pages/admin/categories/categories";
    }

    @GetMapping(value = "categories/add")
    public String addCategoryView(Model model) {
//        List<Category> categories = categoryProxy.allCategories();
        Category category = new Category();
        model.addAttribute("category", category);

        return "pages/admin/categories/add-category";
    }

    @GetMapping(value = "categories/update/{id}")
    public String updateCategoryView(Model model, @PathVariable("id") int id) {
//        List<Category> categories = categoryProxy.allCategories();
        Category category = categoryProxy.getCategoryById(id);
        model.addAttribute("category", category);

        return "pages/admin/categories/add-category";
    }

    @PostMapping(value = "categories")
    public String addProduct(
            @RequestParam("id") int id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile) {
        String imageUrl = "";

        try {
            imageUrl = categoryProxy.uploadImage(imageFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Category category = new Category();
        category.setId(id);
        category.setTitle(title);
        category.setImage(imageUrl);
        category.setDescription(description);

        categoryProxy.addCategory(category);


        return "redirect:/categories";
    }

    @DeleteMapping(value = "categories")
    public String deleteProduct(@RequestParam("id") int id) {

        categoryProxy.deleteCategory(id);

        return "redirect:/";
    }
}
