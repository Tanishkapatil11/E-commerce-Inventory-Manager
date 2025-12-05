package com.example.ecommerce.product_catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data; // Provides getters, setters, equals, hashCode, and toString
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // 1. Tells JPA/Hibernate this class is a table in the database
@Data // 2. Lombok: Automatically generates getters and setters for all fields
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all arguments
public class Product {

    @Id // 3. Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Auto-incremented ID by the DB
    private Long id;

    private String name;

    private String description;

    private double price;

    private int stockQuantity; // Inventory count

    private String category;

    // Note: Lombok handles all the boilerplate methods for us!
}