package com.example.oraclehbm.controller;

import com.example.oraclehbm.dto.BenchmarkResult;
import com.example.oraclehbm.dto.SalesByRegionCategory;
import com.example.oraclehbm.repository.BenchmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {

    private final BenchmarkRepository benchmarkRepository;

    @Autowired
    public BenchmarkController(BenchmarkRepository benchmarkRepository) {
        this.benchmarkRepository = benchmarkRepository;
    }

    /**
     * Benchmark endpoint that executes a heavy join query across
     * FACT_ORDER_LINE -> FACT_ORDER -> DIM_CUSTOMER -> DIM_PRODUCT.
     *
     * Aggregates net_sales = qty * unit_price * (1 - discount_pct)
     * grouped by region (customer state) and product category,
     * ordered by net_sales descending.
     *
     * @param limit maximum number of results to return (default 50)
     * @return benchmark result with data and elapsed time in milliseconds
     */
    @GetMapping("/sales-by-region-category")
    public ResponseEntity<BenchmarkResult<SalesByRegionCategory>> getSalesByRegionCategory(
            @RequestParam(defaultValue = "50") int limit) {

        long startTime = System.currentTimeMillis();

        List<SalesByRegionCategory> results = benchmarkRepository.findSalesByRegionAndCategory(limit);

        long elapsedMillis = System.currentTimeMillis() - startTime;

        BenchmarkResult<SalesByRegionCategory> response = new BenchmarkResult<>(results, elapsedMillis);

        return ResponseEntity.ok(response);
    }
}
