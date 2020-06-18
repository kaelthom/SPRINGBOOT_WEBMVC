package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}
