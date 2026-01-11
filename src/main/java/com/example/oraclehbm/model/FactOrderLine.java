package com.example.oraclehbm.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Domain model for FACT_ORDER_LINE table.
 * Mapped via Hibernate XML mapping file (no @Entity annotation).
 */
public class FactOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderLineId;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
    private BigDecimal discountPercent;
    private Integer lineNumber;

    // Association to order
    private FactOrder order;

    // Association to product
    private DimProduct product;

    public FactOrderLine() {
    }

    public FactOrderLine(Long orderLineId, Long orderId, Long productId, Integer quantity) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public FactOrder getOrder() {
        return order;
    }

    public void setOrder(FactOrder order) {
        this.order = order;
    }

    public DimProduct getProduct() {
        return product;
    }

    public void setProduct(DimProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "FactOrderLine{" +
                "orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", lineTotal=" + lineTotal +
                '}';
    }
}
