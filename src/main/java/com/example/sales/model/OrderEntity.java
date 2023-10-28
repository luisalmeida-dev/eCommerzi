package com.example.sales.model;

import com.example.sales.Enum.OrderStatusEnum;
import com.example.sales.Enum.PaymentTypesEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "tb_order", sequenceName = "tb_order_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentTypesEnum paymentType;
    
    @Column(name = "product_id")
    private String productIdList;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "carrier_id")
    private Long carrierId;

    @Column(name = "shipping_price")
    private BigDecimal shippingPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "code")
    private Long orderCode;

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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(String productIdList) {
        this.productIdList = productIdList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }
}
