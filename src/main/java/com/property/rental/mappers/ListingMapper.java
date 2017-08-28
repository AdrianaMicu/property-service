package com.property.rental.mappers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.property.rental.controllers.*;
import com.property.rental.domain.Address;
import com.property.rental.domain.Contact;
import com.property.rental.domain.Listing;

import com.property.rental.domain.Location;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ListingMapper {

    private PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public Listing toListing(CreateListingRequest listingRequest) throws NumberParseException {
        ContactRequest contactRequest = listingRequest.getContact();
        CreateAddressRequest addressRequest = listingRequest.getAddress();
        LocationRequest locationRequest = listingRequest.getLocation();

        Listing listing = new Listing();
        listing.setAddress(toAddress(addressRequest));
        listing.setContact(toContact(contactRequest, addressRequest.getCountryCode()));
        listing.setLocation(toLocation(locationRequest));

        return listing;
    }

    public Listing toListing(UpdateListingRequest listingRequest) throws NumberParseException {
        ContactRequest contactRequest = listingRequest.getContact();
        AddressRequest addressRequest = listingRequest.getAddress();
        LocationRequest locationRequest = listingRequest.getLocation();

        Listing listing = new Listing();
        listing.setAddress(toAddress(addressRequest));
        listing.setContact(toContact(contactRequest, addressRequest.getCountryCode()));
        listing.setLocation(toLocation(locationRequest));

        return listing;
    }

    private Location toLocation(LocationRequest locationRequest) {
        Location location = new Location();
        location.setLat(locationRequest.getLat());
        location.setLng(locationRequest.getLng());
        return location;
    }

    private Address toAddress(CreateAddressRequest addressRequest) {
        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountryCode(addressRequest.getCountryCode());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(new Locale("", addressRequest.getCountryCode()).getDisplayCountry());
        return address;
    }

    private Address toAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountryCode(addressRequest.getCountryCode());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(new Locale("", addressRequest.getCountryCode()).getDisplayCountry());
        return address;
    }

    private Contact toContact(ContactRequest contactRequest, String countryCode) throws NumberParseException {
        Contact contact = new Contact();
        String phone = contactRequest.getPhone();
        contact.setPhone(phone.replaceAll("[^\\d.]", ""));
        PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, countryCode);
        contact.setFormattedPhone(phoneNumberUtil.format(phoneNumber, PhoneNumberFormat.INTERNATIONAL));
        return contact;
    }
}
