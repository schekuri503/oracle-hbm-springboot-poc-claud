package com.example.oraclehbm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain model for FACT_ORDER table.
 * Mapped via Hibernate XML mapping file (no @Entity annotation).
 */
public class FactOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long customerId;
    private LocalDate orderDt;
    private String channel;
    private String status;

    // Association to customer (for eager/lazy loading)
    private DimCustomer customer;

    // Association to order lines
    private List<FactOrderLine> orderLines = new ArrayList<>();

    public FactOrder() {
    }

    public FactOrder(Long orderId, Long customerId, LocalDate orderDt, String channel, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDt = orderDt;
        this.channel = channel;
        this.status = status;
    }

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(LocalDate orderDt) {
        this.orderDt = orderDt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DimCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(DimCustomer customer) {
        this.customer = customer;
    }

    public List<FactOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<FactOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "FactOrder{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDt=" + orderDt +
                ", channel='" + channel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
