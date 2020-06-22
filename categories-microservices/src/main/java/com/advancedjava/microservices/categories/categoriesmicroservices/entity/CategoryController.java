package com.advancedjava.microservices.categories.categoriesmicroservices.entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> findAllComplete() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "My first category"));
        categories.add(new Category(2, "My second category"));
        categories.add(new Category(3, "My third category"));
        return categories;

    }

    @GetMapping("/categories/{id}")
    public Category findOne(@PathVariable int id) {
        return new Category(id, "My first category");

    }

}
