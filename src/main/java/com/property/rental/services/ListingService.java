package com.property.rental.services;

import com.property.rental.domain.Listing;

import java.util.UUID;

public interface ListingService {

    Listing getListing(UUID listingId);

    Listing createListing(Listing listing);

    Listing updateListing(UUID listingId, Listing listing);

    void deleteListing(UUID listingId);
}
