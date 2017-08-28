package com.property.rental.controllers;

import org.hibernate.validator.constraints.NotBlank;

public class CreateAddressRequest extends AddressRequest {

    @NotBlank(message = "Address is required")
    public String getAddress() {
        return super.getAddress();
    }

    @NotBlank(message = "Postal Code is required")
    public String getPostalCode() {
        return super.getPostalCode();
    }

    @NotBlank(message = "Country Code is required")
    public String getCountryCode() {
        return super.getCountryCode();
    }

    @NotBlank(message = "City is required")
    public String getCity() {
        return super.getCity();
    }

}
