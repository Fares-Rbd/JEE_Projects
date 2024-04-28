package com.application.productsapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor // Lombok annotations to generate Getters, Setters, Constructors
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID with strategy 'IDENTITY
    private long id;
    private String name;
    private double price;
    private int quantity;
}