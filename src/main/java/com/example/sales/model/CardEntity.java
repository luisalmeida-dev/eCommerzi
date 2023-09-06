package com.example.sales.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TB_CARD")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_CARD", sequenceName = "TB_CARD_SEQ", allocationSize = 1)
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "EXPIRATION_DT")
    private Date expirationDate;

    @Column(name = "CVV")
    private Integer cvv;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CARD_NIKCNAME")
    private String cardNickname;

    @Column(name = "USER_ID")
    private Long userId;

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
