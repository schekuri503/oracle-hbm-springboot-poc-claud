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
    private Long supplierId;
    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal discountPct;

    // Association to order
    private FactOrder order;

    // Association to product
    private DimProduct product;

    public FactOrderLine() {
    }

    public FactOrderLine(Long orderLineId, Long orderId, Long productId, Long supplierId,
                         Integer qty, BigDecimal unitPrice, BigDecimal discountPct) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productId = productId;
        this.supplierId = supplierId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.discountPct = discountPct;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(BigDecimal discountPct) {
        this.discountPct = discountPct;
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
                ", supplierId=" + supplierId +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", discountPct=" + discountPct +
                '}';
    }
}
