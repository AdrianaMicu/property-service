package com.property.rental.controllers;

import javax.validation.Valid;

public class UpdateListingRequest extends ListingRequest {

    @Valid
    private AddressRequest address;

    @Valid
    private LocationRequest location;

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    public LocationRequest getLocation() {
        return location;
    }

    public void setLocation(LocationRequest location) {
        this.location = location;
    }
}
