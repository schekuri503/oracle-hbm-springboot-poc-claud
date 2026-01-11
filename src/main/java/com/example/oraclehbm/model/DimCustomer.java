package com.example.oraclehbm.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Domain model for DIM_CUSTOMER table.
 * Mapped via Hibernate XML mapping file (no @Entity annotation).
 */
public class DimCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long customerId;
    private String region;
    private String segment;
    private LocalDate createdDt;

    public DimCustomer() {
    }

    public DimCustomer(Long customerId, String region, String segment, LocalDate createdDt) {
        this.customerId = customerId;
        this.region = region;
        this.segment = segment;
        this.createdDt = createdDt;
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public String toString() {
        return "DimCustomer{" +
                "customerId=" + customerId +
                ", region='" + region + '\'' +
                ", segment='" + segment + '\'' +
                ", createdDt=" + createdDt +
                '}';
    }
}
