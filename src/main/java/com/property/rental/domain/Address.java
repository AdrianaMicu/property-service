package com.property.rental.domain;

import java.util.Objects;

public class Address {

    private String address;
    private String postalCode;
    private String countryCode;
    private String city;
    private String state;
    private String country;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(postalCode, address1.postalCode) &&
                Objects.equals(countryCode, address1.countryCode) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(state, address1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, postalCode, countryCode, city, state);
    }
}
