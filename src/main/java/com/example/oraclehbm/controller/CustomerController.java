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
     * Get top N customers (default 50)
     */
    @GetMapping
    public ResponseEntity<List<DimCustomer>> getTopCustomers(
            @RequestParam(defaultValue = "50") int limit) {
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
     * Get active customers
     */
    @GetMapping("/active")
    public ResponseEntity<List<DimCustomer>> getActiveCustomers() {
        List<DimCustomer> customers = customerRepository.findActiveCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by city
     */
    @GetMapping("/city/{city}")
    public ResponseEntity<List<DimCustomer>> getCustomersByCity(@PathVariable String city) {
        List<DimCustomer> customers = customerRepository.findByCity(city);
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
