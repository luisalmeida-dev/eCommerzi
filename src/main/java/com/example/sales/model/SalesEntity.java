package com.example.sales.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SALES")
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_SALES", sequenceName = "TB_SALES_SEQ", allocationSize = 1)
    @Column(name = "SALES_ID")
    private Long id;

    @Column(name = "AMOUNT")
    private String amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "paymentType")
    private String paymentType;

    @Column(name = "REGISTRATION_DT")
    private LocalDateTime registrartionDate;

    @Column(name = "SHIPPING_PRICE")
    private BigDecimal shippingPrice;
}
