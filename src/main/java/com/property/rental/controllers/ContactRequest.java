package com.property.rental.controllers;

import org.hibernate.validator.constraints.NotBlank;

public class ContactRequest {

    @NotBlank(message = "Phone is required")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
