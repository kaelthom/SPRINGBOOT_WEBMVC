package com.advancedjava.microservices.products.productsmicroservice.entity;

public class Product {
    private Integer id;
    private String name;
    private int categoryId;

    public Product() {
    }

    public Product(int id, String defaultName, int categoryId) {
        this.name = defaultName;
        this.id = id;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
