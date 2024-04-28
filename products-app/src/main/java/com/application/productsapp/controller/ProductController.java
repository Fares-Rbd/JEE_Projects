package com.application.productsapp.controller;

import com.application.productsapp.entities.Product;
import com.application.productsapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
