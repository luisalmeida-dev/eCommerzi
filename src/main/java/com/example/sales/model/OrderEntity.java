package com.example.sales.model;

import com.example.sales.Enum.OrderStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "PAYMENT_DETAILS_ID")
    private Long paymentDetailsId;

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
    private OrderStatusEnum orderStatus;

    @Column(name = "REGISTRATION_DT")
    private LocalDateTime registrationDate;

    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getPaymentDetailsId() {
        return paymentDetailsId;
    }

    public void setPaymentDetailsId(Long paymentDetailsId) {
        this.paymentDetailsId = paymentDetailsId;
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

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}