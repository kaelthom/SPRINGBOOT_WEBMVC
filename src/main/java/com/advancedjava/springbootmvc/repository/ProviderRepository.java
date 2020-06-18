package com.advancedjava.springbootmvc.repository;

import com.advancedjava.springbootmvc.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hateoas-providers")
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    public Provider findByCompanyNameOrBankAccountNumber(String companyName, String bankAccountNumber);
}
