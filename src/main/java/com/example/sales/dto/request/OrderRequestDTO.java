package com.example.sales.dto.request;

import com.example.sales.Enum.PaymentTypesEnum;

import java.util.List;

public class OrderRequestDTO {

    private Integer cardId;
    private Integer carrierId;
    private PaymentTypesEnum paymentType;
    private List<Integer> productIdList;
    private Integer storeId;
    private Integer quantity;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public PaymentTypesEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypesEnum paymentType) {
        this.paymentType = paymentType;
    }

    public List<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Integer> productIdList) {
        this.productIdList = productIdList;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}