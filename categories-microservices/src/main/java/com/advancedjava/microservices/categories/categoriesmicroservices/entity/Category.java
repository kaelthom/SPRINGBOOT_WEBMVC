package com.advancedjava.microservices.categories.categoriesmicroservices.entity;

public class Category {
    Integer id;
    String name;

    public Category() {
    }

    public Category(int id, String defaultName) {
        this.name = defaultName;
        this.id = id;
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

}
