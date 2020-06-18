package com.advancedjava.springbootmvc.entity;

import com.advancedjava.springbootmvc.dto.JsonMapping;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    Integer id;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    String name;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    String nameVN;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

	/*
	@OneToMany(mappedBy="category", fetch= FetchType.EAGER)
	List<Product> products;*/

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

/*
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}*/
}
