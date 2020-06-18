package com.advancedjava.springbootmvc.controller;

import com.advancedjava.springbootmvc.entity.Category;
import com.advancedjava.springbootmvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public List<Category> findAll() {
        return categoryRepository.findAll();

    }
    @GetMapping("/fake")
    public Category findFake() {
        return new Category("Fruit");

    }
    @PostMapping("/add")
    public void add(@RequestBody Category category) {
        categoryRepository.save(category);
    }
    @PutMapping("/edit/{id}")
    public void edit(@RequestBody Category category, @PathVariable int id) throws Exception {
        if (category.getId()!=null && id != category.getId()) {
            throw new Exception("incoherence between ids");
        }
        categoryRepository.save(category);
    }

    @PatchMapping("/patch/{id}")
    public void editPatch(@RequestBody Map<String, Object> fields, @PathVariable int id) throws Exception {
        if (!categoryRepository.findById(id).isPresent()) {
            throw new Exception("category not found");
        }
        Category category = categoryRepository.findById(id).get();
        if(id!=category.getId() ) {
            throw new Exception("incoherence between ids");
        }

        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(Category.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, category, value);
        });
        categoryRepository.save(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }


}
