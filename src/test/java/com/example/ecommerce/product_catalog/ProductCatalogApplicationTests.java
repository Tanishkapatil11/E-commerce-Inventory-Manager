package com.example.ecommerce.product_catalog;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles; // Import needed for profile usage if you use one

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// If you were using a test profile, you'd enable it here:
// @ActiveProfiles("test")
class ProductCatalogApplicationTests {

    @Test
    void contextLoads() {
        // This simple test now successfully loads the application context on a random port.
        System.out.println("Application Context loaded successfully for testing.");
    }

}