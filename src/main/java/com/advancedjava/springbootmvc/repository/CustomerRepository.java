package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hateoas-customers")
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
