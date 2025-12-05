package com.example.ecommerce.product_catalog.repository;

import com.example.ecommerce.product_catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository<Entity Class, Type of Primary Key>
public interface ProductRepository extends JpaRepository<Product, Long> {

    // --- Derived Query Method ---
    // Spring Data JPA automatically generates the SQL query
    // SELECT * FROM Product WHERE category = ?
    List<Product> findByCategory(String category);

    // Another example: SELECT * FROM Product WHERE price < ?
    List<Product> findByPriceLessThan(double maxPrice);

    // Combine fields: SELECT * FROM Product WHERE name LIKE ? AND stockQuantity > ?
    List<Product> findByNameContainingAndStockQuantityGreaterThan(String name, int minStock);
}