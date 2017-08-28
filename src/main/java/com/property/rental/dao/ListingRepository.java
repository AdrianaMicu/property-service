package com.property.rental.dao;

import com.property.rental.domain.Listing;

import java.util.Optional;
import java.util.UUID;

public interface ListingRepository {

    Optional<Listing> getListing(UUID listingId);

    Optional<Listing> saveListing(Listing listing, boolean isNew);

    void deleteListing(Listing listing);
}
