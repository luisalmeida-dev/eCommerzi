package com.example.sales.dto.request;

public class OrderRequestDTO {
    private Long paymentDetailsId;
    private Long productId;
    private Long sellerId;
    private Long buyerId;
    private Long carrierId;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
