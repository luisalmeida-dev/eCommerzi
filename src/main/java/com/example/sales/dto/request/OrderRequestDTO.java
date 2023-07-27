package com.example.sales.dto.request;

import com.example.sales.Enum.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderRequestDTO {
    private BigDecimal total;
    private Long paymentDetailsId;
    private Long productId;
    private Long sellerId;
    private Long buyerId;
    private Long carrierId;
    private BigDecimal shippingPrice;
    private OrderStatusEnum orderStatus;
    private Date registrationDate;
    private LocalDateTime deliveryDate;
    private String trackingNumber;

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
