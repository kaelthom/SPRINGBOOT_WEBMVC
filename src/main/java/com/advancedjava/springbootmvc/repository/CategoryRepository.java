package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
