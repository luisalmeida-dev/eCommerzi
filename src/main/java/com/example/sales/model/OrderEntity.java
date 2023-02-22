package com.example.sales.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_ORDER", sequenceName = "TB_ORDER_SEQ", allocationSize = 1)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @Column(name = "BUYER_ID")
    private Long buyerId;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "SHIPPING_PRICE")
    private BigDecimal shippingPrice;

    @Column(name = "ORDER_STATUS_ID")
    private Long orderStatusId;

    @Column(name = "REGISTRATION_DT")
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}