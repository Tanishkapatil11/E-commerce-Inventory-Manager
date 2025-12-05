package com.example.ecommerce.product_catalog.service;

import com.example.ecommerce.product_catalog.model.Product;
import com.example.ecommerce.product_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // 1. Marks this class as a Spring Service (a business component)
public class ProductService {

    private final ProductRepository productRepository;

    // 2. Dependency Injection: Spring automatically provides an instance of ProductRepository
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- CRUD Operations ---

    // READ: Get all products
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // READ: Get product by ID
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    // CREATE/UPDATE: Save a new product or update an existing one
    @Transactional // Ensures the database operation is completed successfully or rolled back
    public Product saveProduct(Product product) {
        // Here you could add logic like: validate price > 0, check category consistency, etc.
        return productRepository.save(product);
    }

    // DELETE: Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // --- Business Logic Example: Stock Update ---

    @Transactional
    public Optional<Product> updateStock(Long productId, int quantityChange) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            int newStock = product.getStockQuantity() + quantityChange;

            if (newStock < 0) {
                // Prevent negative stock: This is the business rule!
                throw new IllegalStateException("Cannot reduce stock below zero.");
            }

            product.setStockQuantity(newStock);
            return Optional.of(productRepository.save(product)); // Save the updated product
        }

        return Optional.empty(); // Product not found
    }
}