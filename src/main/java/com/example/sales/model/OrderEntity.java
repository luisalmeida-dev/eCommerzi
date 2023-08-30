package com.example.sales.model;

import com.example.sales.Enum.OrderStatusEnum;
import com.example.sales.Enum.PaymentTypesEnum;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE")
    private PaymentTypesEnum paymentType;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS_ID")
    private OrderStatusEnum orderStatus;

    @Column(name = "REGISTRATION_DT")
    private Date registrationDate;

    @Column(name = "DELIVERY_DATE")
    private LocalDateTime deliveryDate;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "CARD_ID")
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public PaymentTypesEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypesEnum paymentType) {
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
