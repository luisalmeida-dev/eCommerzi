package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_PAYMENT_DETAILS")
public class PaymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_PAYMENT_DETAILS", sequenceName = "TB_PAYMENT_DETAILS_SEQ", allocationSize = 1)
    @Column(name = "PAYMENT_DETAILS_ID")
    private Long id;

    @Column(name = "PAYMENT_TYPE_ID")
    private Long paymentTypeId;

    @Column(name = "CARD_ID")
    private Long cardId;

    public Long getId() {
        return id;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
