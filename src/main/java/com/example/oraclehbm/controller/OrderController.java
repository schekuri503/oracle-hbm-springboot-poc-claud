package com.example.oraclehbm.controller;

import com.example.oraclehbm.model.FactOrder;
import com.example.oraclehbm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Get top N orders (default 10)
     */
    @GetMapping
    public ResponseEntity<List<FactOrder>> getTopOrders(
            @RequestParam(defaultValue = "10") int limit) {
        List<FactOrder> orders = orderRepository.findTop(limit);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get all orders
     */
    @GetMapping("/all")
    public ResponseEntity<List<FactOrder>> getAllOrders() {
        List<FactOrder> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    /**
     * Get order by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FactOrder> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get order by order number
     */
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<FactOrder> getOrderByNumber(@PathVariable String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get orders by customer ID
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FactOrder>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<FactOrder> orders = orderRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get orders by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FactOrder>> getOrdersByStatus(@PathVariable String status) {
        List<FactOrder> orders = orderRepository.findByStatus(status);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get orders by date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<FactOrder>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FactOrder> orders = orderRepository.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get order count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getOrderCount() {
        return ResponseEntity.ok(orderRepository.count());
    }
}
