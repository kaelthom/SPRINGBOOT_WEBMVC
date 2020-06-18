package com.advancedjava.springbootmvc.entity;

import com.advancedjava.springbootmvc.dto.JsonMapping;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    private int id;

    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    private String companyName;

    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    private String bankAccountNumber;

    @JsonIgnoreProperties("provider")
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private List<Product> products;

    public Provider() {
    }

    public Provider(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
