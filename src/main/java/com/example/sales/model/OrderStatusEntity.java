package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_ORDER_STATUS")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_ORDER_STATUS", sequenceName = "TB_ORDER_STATUS_SEQ", allocationSize = 1)
    @Column(name = "ORDER_STATUS_ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
