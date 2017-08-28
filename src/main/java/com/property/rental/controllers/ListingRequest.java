package com.property.rental.controllers;

import com.property.rental.controllers.util.Listing;

@Listing
public class ListingRequest {

    private ContactRequest contact;

    public ContactRequest getContact() {
        return contact;
    }

    public void setContact(ContactRequest contact) {
        this.contact = contact;
    }
}
