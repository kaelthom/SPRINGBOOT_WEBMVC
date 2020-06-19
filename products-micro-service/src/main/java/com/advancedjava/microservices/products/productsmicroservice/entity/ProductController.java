package com.advancedjava.microservices.products.productsmicroservice.entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ProductList findAllComplete() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "My first product", 1));
        products.add(new Product(2, "My second product", 1));
        products.add(new Product(3, "My third product", 1));
        return new ProductList(products);

    }

    @GetMapping("/products/{id}")
    public Product findOne(@PathVariable int id) {
        return new Product(id, "My first product", 1);

    }

}
