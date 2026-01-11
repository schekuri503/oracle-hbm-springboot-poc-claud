package com.example.oraclehbm.controller;

import com.example.oraclehbm.model.FactOrderLine;
import com.example.oraclehbm.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-lines")
public class OrderLineController {

    private final OrderLineRepository orderLineRepository;

    @Autowired
    public OrderLineController(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    /**
     * Get top N order lines (default 50)
     */
    @GetMapping
    public ResponseEntity<List<FactOrderLine>> getTopOrderLines(
            @RequestParam(defaultValue = "50") int limit) {
        List<FactOrderLine> orderLines = orderLineRepository.findTop(limit);
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get all order lines
     */
    @GetMapping("/all")
    public ResponseEntity<List<FactOrderLine>> getAllOrderLines() {
        List<FactOrderLine> orderLines = orderLineRepository.findAll();
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get order line by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FactOrderLine> getOrderLineById(@PathVariable Long id) {
        return orderLineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get order lines by order ID
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<FactOrderLine>> getOrderLinesByOrderId(@PathVariable Long orderId) {
        List<FactOrderLine> orderLines = orderLineRepository.findByOrderId(orderId);
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get order lines by order ID with limit (default 200)
     */
    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<FactOrderLine>> getOrderLinesByOrderIdWithLimit(
            @PathVariable Long orderId,
            @RequestParam(defaultValue = "200") int limit) {
        List<FactOrderLine> orderLines = orderLineRepository.findByOrderIdWithLimit(orderId, limit);
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get order lines by product ID
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<FactOrderLine>> getOrderLinesByProductId(@PathVariable Long productId) {
        List<FactOrderLine> orderLines = orderLineRepository.findByProductId(productId);
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get order line count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getOrderLineCount() {
        return ResponseEntity.ok(orderLineRepository.count());
    }
}
