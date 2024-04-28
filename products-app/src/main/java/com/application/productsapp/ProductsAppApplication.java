package com.application.productsapp;

import com.application.productsapp.entities.Product;
import com.application.productsapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsAppApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductsAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//         productRepository.save(new Product(0, "Laptop", 1000, 5));
//         productRepository.save(new Product(0, "Mouse", 20, 10));
//         productRepository.save(new Product(0, "Keyboard", 50, 7));
//         productRepository.save(new Product(0, "Monitor", 200, 3));
//         productRepository.save(new Product(0, "Printer", 150, 2));
//         productRepository.save(new Product(0, "Scanner", 120, 4));
//         productRepository.save(new Product(0, "Projector", 300, 1));
//         productRepository.save(new Product(0, "Tablet", 400, 6));
//         productRepository.save(new Product(0, "Smartphone", 700, 8));
//         productRepository.save(new Product(0, "Smartwatch", 300, 9));
         productRepository.findAll().forEach(System.out::println);
         Product product = productRepository.findById(1L).get();
         System.out.println("**************************");
         System.out.println(product.toString());
        System.out.println("**************************");
        productRepository.findByNameContains("c").forEach(System.out::println);
        System.out.println("**************************");
        productRepository.search("%sma%").forEach(System.out::println);
        System.out.println("**************************");
        productRepository.findByPriceGreaterThan(200).forEach(System.out::println);
        System.out.println("**************************");
        productRepository.searchByPrice(800).forEach(System.out::println);

    }
}
