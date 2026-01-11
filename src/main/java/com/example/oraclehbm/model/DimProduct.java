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
    private String productName;
    private String productCode;
    private String category;
    private String subCategory;
    private BigDecimal unitPrice;
    private String description;
    private LocalDate createdDate;
    private Boolean activeFlag;

    public DimProduct() {
    }

    public DimProduct(Long productId, String productName, String productCode) {
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "DimProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", category='" + category + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
