package com.advancedjava.springbootmvc.controller;

import com.advancedjava.springbootmvc.entity.Product;
import com.advancedjava.springbootmvc.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @GetMapping("/products")
    public List<Product> findAll() {
        return productRepository.findAll();

    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findOne(@PathVariable int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.isPresent()?optionalProduct.get():null;
        HttpStatus status;
        if (product == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(product, status);

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
    @PutMapping("/products/edit/{id}")
    public void edit(@RequestBody @Valid Product product, @PathVariable int id) throws Exception {
        if (product.getId()!=null && id != product.getId()) {
            throw new Exception("incoherence between ids");
        }
        productRepository.save(product);
    }

    @PatchMapping("/products/patch/{id}")
    public void editPatch(@RequestBody Map<String, Object> fields, @PathVariable int id) throws Exception {
        if (!productRepository.findById(id).isPresent()) {
            throw new Exception("product not found");
        }
        Product product = productRepository.findById(id).get();
        if(id!=product.getId() ) {
            throw new Exception("incoherence between ids");
        }

        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(Product.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, product, value);
        });
        productRepository.save(product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void delete(@PathVariable int id) {
        productRepository.deleteById(id);
    }

}
