package com.example.ecommerce.product_catalog;

import com.example.ecommerce.product_catalog.model.Product;
import com.example.ecommerce.product_catalog.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }

    // This method will run immediately after the application starts up
    @Bean
    public CommandLineRunner demoData(ProductService productService) {
        return args -> {
            System.out.println("--- Loading initial product data... ---");

            // 1. Create a few products
            Product p1 = new Product(null, "Wireless Mechanical Keyboard", "A clicky, full-sized mechanical keyboard with RGB.", 129.99, 50, "Electronics");
            Product p2 = new Product(null, "Ergonomic Desk Chair", "Adjustable chair with lumbar support.", 450.00, 15, "Furniture");
            Product p3 = new Product(null, "Portable SSD 1TB", "High-speed external solid state drive.", 79.50, 100, "Electronics");

            // 2. Save them using the service
            productService.saveProduct(p1);
            productService.saveProduct(p2);
            productService.saveProduct(p3);

            System.out.println("--- Product Catalog Ready! ---");
        };
    }
}
