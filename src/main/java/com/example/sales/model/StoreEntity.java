package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_STORE")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_STORE", sequenceName = "TB_STORE_SEQ", allocationSize = 1)
    @Column(name = "STORE_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CNPJ")
    private String cnpj;

}
