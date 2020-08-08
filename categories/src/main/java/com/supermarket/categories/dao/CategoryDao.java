package com.supermarket.categories.dao;

import com.supermarket.categories.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findById(int id);
    List<Category> findAll();
}
