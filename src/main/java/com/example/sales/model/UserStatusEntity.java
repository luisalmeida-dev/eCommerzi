package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_STATUS_USER")
public class UserStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_STATUS_USER", sequenceName = "TB_STATUS_USER_SEQ", allocationSize = 1)
    @Column(name = "STATUS_USER_ID")
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
