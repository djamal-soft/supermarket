package com.supermarket.employeeUI.models;

import com.supermarket.employeeUI.proxies.CategoryProxy;

import java.util.ArrayList;
import java.util.List;

public class Product {


    private int id;
    private String name;
    private int price;
    private String image;
    private String categories;
    private List<Category> categoriesObjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

//    public List<String> categoriesNames() {
//
//        List<String> categoriesNames = new ArrayList<>();
//        for (Category category: categoriesObjects) {
//            categoriesNames.add(category.getTitle());
//        }
//
//        System.out.println(categoriesNames);
//
////        List<String> test = new ArrayList<>();
////        test.add("djamal");
////        test.add("bekhiekh");
//        return categoriesNames;
//    }


    public List<Category> getCategoriesObjects() {
        return categoriesObjects;
    }

    public void setCategoriesObjects(List<Category> categoriesObjects) {
        this.categoriesObjects = categoriesObjects;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", categories='" + categories + '\'' +
                ", categoriesObjects=" + categoriesObjects +
                '}';
    }
}
