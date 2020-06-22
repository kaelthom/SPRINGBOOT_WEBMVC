package com.advancedjava.microservices.productscomplete.productcompletemicroservices.controller;

import com.advancedjava.microservices.categories.categoriesmicroservices.entity.Category;
import com.advancedjava.microservices.products.productsmicroservice.entity.Product;
import com.advancedjava.microservices.products.productsmicroservice.entity.ProductList;
import com.advancedjava.microservices.productscomplete.productcompletemicroservices.model.ProductItem;
import com.advancedjava.microservices.productscomplete.productcompletemicroservices.service.ProductItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ProductCompleteController {

    @Autowired
    ProductItemService productItemService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/productsC")
    public List<ProductItem> findAllComplete() {

        List<ProductItem> productItems = new ArrayList<>();
        for (Product product : productItemService.getProductList().getProducts()) {
            Category category = getCategory(product);
            ProductItem productItem = new ProductItem();
            productItem.setCategoryName(category.getName());
            productItem.setId(product.getId());
            productItem.setProductName(product.getName());
            productItems.add(productItem);
        }
        return productItems;

    }

    public Category getCategory(Product product) {
        return restTemplate.getForObject("http://categories-service/categories/" + product.getCategoryId(), Category.class);
    }

    public Category getCategoryError(Product product) {

        return new Category(-1, "error");

    }

}
