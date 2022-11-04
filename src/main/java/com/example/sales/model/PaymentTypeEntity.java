package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_PAYMENT_TYPE")
public class PaymentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_PAYMENT_TYPE", sequenceName = "TB_PAYMENT_TYPE_SEQ", allocationSize = 1)
    @Column(name = "PAYMENT_TYPE_ID")
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
}
