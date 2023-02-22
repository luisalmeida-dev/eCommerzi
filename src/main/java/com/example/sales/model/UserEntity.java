package com.example.sales.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "TB_USER", sequenceName = "TB_USER_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String login;

    @Column(name = "EMAIL")
    private String name;

    @Column(name = "LOGIN")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "STATUS_USER_ID")
    private Long statusId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
