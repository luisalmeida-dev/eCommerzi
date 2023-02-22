package com.example.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_PRODUCT_CATEGORY")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_PRODUCT_CATEGORY", sequenceName = "TB_PRODUCT_CATEGORY_SEQ", allocationSize = 1)
    @Column(name = "PRODUCT_CATEGORY_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESC")
    private String desc;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


