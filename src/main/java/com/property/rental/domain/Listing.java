package com.property.rental.domain;

import java.util.Objects;
import java.util.UUID;

public class Listing {

    private UUID id;
    private Contact contact;
    private Address address;
    private Location location;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(contact, listing.contact) &&
                Objects.equals(address, listing.address) &&
                Objects.equals(location, listing.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact, address, location);
    }
}
