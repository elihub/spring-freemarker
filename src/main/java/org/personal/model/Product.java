/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author elida
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    private static final long serialVersionUID = -172412219243754562L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Description cannot be empty")
    @Length(min = 3, max = 100, message = "Descripcion needs to have at least 3 characters")
    private String description;
    @NotEmpty(message = "Code cannot be empty")
    private String code;
    @NotNull(message = "Price cannot be empty")
    private Double price;
    @NotNull(message = "Amount cannot be empty")
    private Integer amount;
    private String hash;
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    public Product() {
    }

    public Product(String description, String code, Double price, Integer amount, String hash) {
        this.description = description;
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", code=").append(code);
        sb.append(", price='").append(price).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", hash='").append(hash).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
