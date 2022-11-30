package com.example.sales.dto.request;

import com.example.sales.model.RoleEntity;
import com.example.sales.model.StatusUserEntity;

public class UserRequsetDTO {

    private String cpf;
    private String name;
    private String email;
    private String password;

    private StatusUserEntity statusId;

    private RoleEntity roleId;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public StatusUserEntity getStatusId() {
        return statusId;
    }

    public void setStatusId(StatusUserEntity statusId) {
        this.statusId = statusId;
    }

    public RoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }
}
