package com.example.sales.model;

import com.example.sales.Enum.PaymentTypesEnum;

import javax.persistence.*;

@Entity
@Table(name = "TB_PAYMENT_DETAILS")
public class PaymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_PAYMENT_DETAILS", sequenceName = "TB_PAYMENT_DETAILS_SEQ", allocationSize = 1)
    @Column(name = "PAYMENT_DETAILS_ID")
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    private PaymentTypesEnum paymentType;

    @Column(name = "CARD_ID")
    private Long cardId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentTypesEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypesEnum paymentType) {
        this.paymentType = paymentType;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
