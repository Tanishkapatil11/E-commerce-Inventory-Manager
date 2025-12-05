package com.example.ecommerce.product_catalog.controller;

import com.example.ecommerce.product_catalog.model.Product;
import com.example.ecommerce.product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// 1. Marks this class as a REST Controller
@RestController
// 2. Base path for all endpoints in this controller
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // Dependency Injection for the ProductService
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- 1. GET: Fetch All Products ---
    // Endpoint: GET http://localhost:8080/api/v1/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products); // Return 200 OK with the list of products
    }

    // --- 2. GET: Fetch Product by ID ---
    // Endpoint: GET http://localhost:8080/api/v1/products/123
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                // If found, return 200 OK with the product
                .map(ResponseEntity::ok)
                // If not found, return 404 Not Found
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product with ID " + id + " not found.")
                );
    }

    // --- 3. POST: Create a New Product ---
    // Endpoint: POST http://localhost:8080/api/v1/products
    // Requires a Product JSON body in the request
    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED) is a simpler alternative to ResponseEntity.status()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Validation for new product: ID should be null/zero
        if (product.getId() != null && product.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be provided for a new product.");
        }
        Product savedProduct = productService.saveProduct(product);
        // Return 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // --- 4. PUT: Update an Existing Product ---
    // Endpoint: PUT http://localhost:8080/api/v1/products/123
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        // Ensure the ID in the path matches the ID in the body for consistency
        if (!id.equals(productDetails.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID in path must match ID in request body.");
        }

        // Check if the product exists before trying to save it
        if (productService.findProductById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found for update.");
        }

        // Save will update the existing product because the ID is present
        Product updatedProduct = productService.saveProduct(productDetails);
        return ResponseEntity.ok(updatedProduct); // Return 200 OK
    }

    // --- 5. DELETE: Delete a Product ---
    // Endpoint: DELETE http://localhost:8080/api/v1/products/123
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 No Content for a successful deletion
    public void deleteProduct(@PathVariable Long id) {
        if (productService.findProductById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found for deletion.");
        }
        productService.deleteProduct(id);
    }
}