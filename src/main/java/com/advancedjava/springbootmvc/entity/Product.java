package com.advancedjava.springbootmvc.entity;

import com.advancedjava.springbootmvc.dto.JsonMapping;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Products")
@NamedQuery(name = "Product.findByDescription", query = "select p from Product p where p.description = :description")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    Integer id;
    @NotNull
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    String name;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    Double unitPrice;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    String image;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    Date productDate;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    Boolean available;
    //Integer categoryId;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    Integer quantity;
    @JsonView(JsonMapping.OnlyPrimitiveFields.class)
    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("products")
    @JoinColumn(name = "categoryId")
    @JsonView(JsonMapping.ProductWithCategory.class)
    Category category;

    @OneToMany(mappedBy = "product")
    @JsonView(JsonMapping.ProductComplete.class)
    List<OrderDetail> orderDetails;

    @JsonIgnoreProperties("products")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonView(JsonMapping.ProductWithProvider.class)
    private Provider provider;

    public Product() {
    }

    public Product(String defaultName) {
        this.name = defaultName;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "unitPrice=" + unitPrice +
                ", description='" + description + '\'' +
                '}';
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
