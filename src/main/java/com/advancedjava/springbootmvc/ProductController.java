package com.advancedjava.springbootmvc;

import com.advancedjava.springbootmvc.entity.Product;
import com.advancedjava.springbootmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productRepository.findAll();

    }
    @GetMapping("/products-fake")
    public List<Product> findAllFake() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("pc"));

        return products;

    }
    @PostMapping("/products/add")
    public void add(@RequestBody @Valid Product product) {
        productRepository.save(product);
    }
}
