package com.advancedjava.microservices.productscomplete.productcompletemicroservices.controller;

import com.advancedjava.microservices.categories.categoriesmicroservices.entity.Category;
import com.advancedjava.microservices.products.productsmicroservice.entity.Product;
import com.advancedjava.microservices.products.productsmicroservice.entity.ProductList;
import com.advancedjava.microservices.productscomplete.productcompletemicroservices.model.ProductItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductCompleteController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/productsC")
    public List<ProductItem> findAllComplete() {

        List<ProductItem> productItems = new ArrayList<>();
        for (Product product : restTemplate.getForObject("http://localhost:8082/products", ProductList.class).getProducts()) {
            Category category = restTemplate.getForObject("http://localhost:8081/categories/" + product.getCategoryId(), Category.class);
            ProductItem productItem = new ProductItem();
            productItem.setCategoryName(category.getName());
            productItem.setId(product.getId());
            productItem.setProductName(product.getName());
            productItems.add(productItem);
        }
        return productItems;

    }


}
