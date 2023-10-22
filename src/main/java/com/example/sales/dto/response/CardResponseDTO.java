package com.example.sales.dto.response;

import java.util.Date;

public class CardResponseDTO {
    private String cardId;
    private String cardNumber;
    private String name;
    private String cardNickname;
    private Date expierationDate;

    public Date getExpierationDate() {
        return expierationDate;
    }

    public void setExpierationDate(Date expierationDate) {
        this.expierationDate = expierationDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNickname() {
        return cardNickname;
    }

    public void setCardNickname(String cardNickname) {
        this.cardNickname = cardNickname;
    }
}
