package com.example.sales.dto.response;

import com.example.sales.Enum.StatesEnum;

public class AddressResponseDTO {
    private Long id;
    private String city;
    private String country;
    private StatesEnum state;
    private String zipcode;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public StatesEnum getState() {
        return state;
    }

    public void setState(StatesEnum state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setId(Long id) {
        this.id = id;
    }
}