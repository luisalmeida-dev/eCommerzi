package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_CARRIER")
public class CarrierEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_CARRIER", sequenceName = "TB_CARRIER_SEQ", allocationSize = 1)
    @Column(name = "CARRIER_ID  ")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
