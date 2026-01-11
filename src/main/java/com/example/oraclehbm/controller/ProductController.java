package com.example.oraclehbm.controller;

import com.example.oraclehbm.model.DimProduct;
import com.example.oraclehbm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get top N products (default 50)
     */
    @GetMapping
    public ResponseEntity<List<DimProduct>> getTopProducts(
            @RequestParam(defaultValue = "50") int limit) {
        List<DimProduct> products = productRepository.findTop(limit);
        return ResponseEntity.ok(products);
    }

    /**
     * Get all products
     */
    @GetMapping("/all")
    public ResponseEntity<List<DimProduct>> getAllProducts() {
        List<DimProduct> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    /**
     * Get product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DimProduct> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get product by product code
     */
    @GetMapping("/code/{productCode}")
    public ResponseEntity<DimProduct> getProductByCode(@PathVariable String productCode) {
        return productRepository.findByProductCode(productCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get products by category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<DimProduct>> getProductsByCategory(@PathVariable String category) {
        List<DimProduct> products = productRepository.findByCategory(category);
        return ResponseEntity.ok(products);
    }

    /**
     * Get active products
     */
    @GetMapping("/active")
    public ResponseEntity<List<DimProduct>> getActiveProducts() {
        List<DimProduct> products = productRepository.findActiveProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get product count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount() {
        return ResponseEntity.ok(productRepository.count());
    }
}
