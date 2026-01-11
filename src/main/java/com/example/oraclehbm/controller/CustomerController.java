package com.example.oraclehbm.controller;

import com.example.oraclehbm.model.DimCustomer;
import com.example.oraclehbm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Get top N customers (default 10)
     */
    @GetMapping
    public ResponseEntity<List<DimCustomer>> getTopCustomers(
            @RequestParam(defaultValue = "10") int limit) {
        List<DimCustomer> customers = customerRepository.findTop(limit);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get all customers
     */
    @GetMapping("/all")
    public ResponseEntity<List<DimCustomer>> getAllCustomers() {
        List<DimCustomer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customer by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DimCustomer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get customers by region
     */
    @GetMapping("/region/{region}")
    public ResponseEntity<List<DimCustomer>> getCustomersByRegion(@PathVariable String region) {
        List<DimCustomer> customers = customerRepository.findByRegion(region);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by segment
     */
    @GetMapping("/segment/{segment}")
    public ResponseEntity<List<DimCustomer>> getCustomersBySegment(@PathVariable String segment) {
        List<DimCustomer> customers = customerRepository.findBySegment(segment);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by region and segment
     */
    @GetMapping("/filter")
    public ResponseEntity<List<DimCustomer>> getCustomersByRegionAndSegment(
            @RequestParam String region,
            @RequestParam String segment) {
        List<DimCustomer> customers = customerRepository.findByRegionAndSegment(region, segment);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customer count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getCustomerCount() {
        return ResponseEntity.ok(customerRepository.count());
    }
}
