package com.example.sales.dto.response;

import com.example.sales.Enum.CategoryEnum;
import com.example.sales.Enum.DiscountStatusEnum;

import java.math.BigDecimal;

public class ProductResponseDTO {
    private Integer productId;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String sku;
    private CategoryEnum category;
    private BigDecimal discountPercentage;
    private DiscountStatusEnum discountStatus;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}