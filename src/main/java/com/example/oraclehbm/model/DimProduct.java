package com.example.oraclehbm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Domain model for DIM_PRODUCT table.
 * Mapped via Hibernate XML mapping file (no @Entity annotation).
 */
public class DimProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private String category;
    private String brand;
    private BigDecimal basePrice;
    private LocalDate createdDt;

    public DimProduct() {
    }

    public DimProduct(Long productId, String category, String brand, BigDecimal basePrice, LocalDate createdDt) {
        this.productId = productId;
        this.category = category;
        this.brand = brand;
        this.basePrice = basePrice;
        this.createdDt = createdDt;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public String toString() {
        return "DimProduct{" +
                "productId=" + productId +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", basePrice=" + basePrice +
                ", createdDt=" + createdDt +
                '}';
    }
}
