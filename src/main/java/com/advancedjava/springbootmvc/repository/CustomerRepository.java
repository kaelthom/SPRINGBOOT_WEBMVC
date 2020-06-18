package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
