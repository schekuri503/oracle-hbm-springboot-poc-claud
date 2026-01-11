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
     * Get top N order lines (default 10)
     */
    @GetMapping
    public ResponseEntity<List<FactOrderLine>> getTopOrderLines(
            @RequestParam(defaultValue = "10") int limit) {
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
     * Get order lines by product ID
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<FactOrderLine>> getOrderLinesByProductId(@PathVariable Long productId) {
        List<FactOrderLine> orderLines = orderLineRepository.findByProductId(productId);
        return ResponseEntity.ok(orderLines);
    }

    /**
     * Get order lines by supplier ID
     */
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<FactOrderLine>> getOrderLinesBySupplierId(@PathVariable Long supplierId) {
        List<FactOrderLine> orderLines = orderLineRepository.findBySupplierId(supplierId);
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
