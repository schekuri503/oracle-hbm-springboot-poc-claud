package com.example.oraclehbm.dto;

import java.math.BigDecimal;

/**
 * DTO for sales aggregation by region and category.
 * Used by benchmark endpoint to return heavy join query results.
 */
public class SalesByRegionCategory {

    private String region;
    private String category;
    private BigDecimal netSales;

    public SalesByRegionCategory() {
    }

    public SalesByRegionCategory(String region, String category, BigDecimal netSales) {
        this.region = region;
        this.category = category;
        this.netSales = netSales;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getNetSales() {
        return netSales;
    }

    public void setNetSales(BigDecimal netSales) {
        this.netSales = netSales;
    }

    @Override
    public String toString() {
        return "SalesByRegionCategory{" +
                "region='" + region + '\'' +
                ", category='" + category + '\'' +
                ", netSales=" + netSales +
                '}';
    }
}
