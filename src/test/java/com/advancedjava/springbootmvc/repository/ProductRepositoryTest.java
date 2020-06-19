package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {
    }

    @Test
    public void shouldFindProduct() {
        String monPcTest = "pcWithProvider";
        List<Product> products = productRepository.findProductByName(monPcTest);
        assertThat(products).isNotNull();
    }

    @Test
    public void shouldGetSuccess() throws Exception {
        String monPcTest = "pcTest";
        Product productToSave = new Product(monPcTest);

        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(productToSave);
        this.mockMvc.perform(post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id", org.hamcrest.Matchers.is(1)));
    }

    @Test
    @Rollback(true)
    public void shouldAddNewProduct() {
        String monPcTest = "mon pc test";
        productRepository.save(new Product(monPcTest));
        assertThat(productRepository.findProductByName(monPcTest).size()).isNotEqualTo(0);
    }

    @Test
    @Rollback(true)
    public void shouldDeleteProduct() {
        String monPcTest = "mon pc test";
        Product product = productRepository.save(new Product(monPcTest));
        productRepository.delete(product);
        assertThat(productRepository.findById(product.getId())).isEmpty();
    }

}
