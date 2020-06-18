package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hateoas-categories")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
