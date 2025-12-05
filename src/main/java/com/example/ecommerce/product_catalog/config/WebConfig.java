package com.example.ecommerce.product_catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 1. Marks this class as a source of configuration
public class WebConfig implements WebMvcConfigurer {

    // 2. Override the CORS configuration method
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Set up the CORS policy
        registry.addMapping("/api/v1/**") // Apply this policy to all endpoints under /api/v1/
                .allowedOrigins("*") // 3. Allow requests from any origin (Crucial for development/file-based frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 4. Allowed HTTP methods
                .allowedHeaders("*"); // 5. Allow all headers
    }
}