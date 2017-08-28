package com.property.rental.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CreateListingRequest extends ListingRequest {

    @Valid
    @NotNull(message = "Address is required")
    private CreateAddressRequest address;

    @Valid
    @NotNull(message = "Location is required")
    private CreateLocationRequest location;

    @Valid
    @NotNull(message = "Contact is required")
    public ContactRequest getContact() {
        return super.getContact();
    }

    public CreateAddressRequest getAddress() {
        return address;
    }

    public void setAddress(CreateAddressRequest address) {
        this.address = address;
    }

    public CreateLocationRequest getLocation() {
        return location;
    }

    public void setLocation(CreateLocationRequest location) {
        this.location = location;
    }
}
