package com.property.rental.services;

import com.property.rental.dao.ListingRepository;
import com.property.rental.domain.Address;
import com.property.rental.domain.Contact;
import com.property.rental.domain.Listing;
import com.property.rental.domain.Location;
import com.property.rental.exceptions.ListingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepo;

    public Listing getListing(UUID listingId) {
        return listingRepo.getListing(listingId).orElseThrow(() -> new ListingException("Could not find any listing with id " + listingId));
    }

    public Listing createListing(Listing listing) {
        listing.setId(UUID.randomUUID());
        return listingRepo.saveListing(listing, true).orElseThrow(() -> new ListingException("Listing with this data already exists"));
    }

    public Listing updateListing(UUID listingId, Listing listing) {
        Listing listingToUpdate = getListing(listingId);

        Contact contactToUpdate = listingToUpdate.getContact();
        Contact contact = listing.getContact();
        if (contact != null) {
            if (StringUtils.isNotBlank(contact.getPhone())) {
                contactToUpdate.setPhone(contact.getPhone());
                contactToUpdate.setFormattedPhone(contact.getFormattedPhone());
            }
        }
        Address addressToUpdate = listingToUpdate.getAddress();
        Address address = listing.getAddress();
        if (address != null) {
            if (StringUtils.isNotBlank(address.getAddress())) {
                addressToUpdate.setAddress(address.getAddress());
            }
            if (StringUtils.isNotBlank(address.getPostalCode())) {
                addressToUpdate.setPostalCode(address.getPostalCode());
            }
            if (StringUtils.isNotBlank(address.getCountryCode())) {
                addressToUpdate.setCountryCode(address.getCountryCode());
                addressToUpdate.setCountry(address.getCountry());
            }
            if (StringUtils.isNotBlank(address.getCity())) {
                addressToUpdate.setCity(address.getCity());
            }
            if (StringUtils.isNotBlank(address.getState())) {
                addressToUpdate.setState(address.getState());
            }
        }
        Location locationToUpdate = listingToUpdate.getLocation();
        Location location = listing.getLocation();
        if (location != null) {
            if (location.getLat() != null) {
                locationToUpdate.setLat(location.getLat());
            }
            if (location.getLng() != null) {
                locationToUpdate.setLng(location.getLng());
            }
        }

        return listingRepo.saveListing(listingToUpdate, false).orElseThrow(() -> new ListingException("Listing with this data already exists"));
    }

    public void deleteListing(UUID listingId) {
        Listing listing = getListing(listingId);
        listingRepo.deleteListing(listing);
    }
}
