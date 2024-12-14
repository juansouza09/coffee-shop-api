package com.jssolutions.coffee_shop_api.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Product(String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.name = name;
        this.price = price;
    }

    public void update(String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.name = name;
        this.price = price;
    }
}
