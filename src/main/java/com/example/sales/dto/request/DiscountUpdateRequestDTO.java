package com.example.sales.dto.request;

import com.example.sales.Enum.DiscountStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountUpdateRequestDTO {
    private String name;
    private String description;
    private BigDecimal discountPercentage;
    private DiscountStatusEnum discountStatus;
    private Date expirationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public DiscountStatusEnum getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(DiscountStatusEnum discountStatus) {
        this.discountStatus = discountStatus;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
