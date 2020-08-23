package com.supermarket.products.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "le nom est requis")
    private String name;

    @Min(value = 1, message = "le prix doit etre superieur a 1")
    private int price;

    @NotBlank(message = "l'image est requis")
    private String image;

    @NotBlank(message = "categories est requis")
    private String categories;

    @Transient
    private List<Object> categoriesObjects;

    public Product() {
    }

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

    public List<Object> getCategoriesObjects() {
        return categoriesObjects;
    }

    public void setCategoriesObjects(List<Object> categoriesObjects) {
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
