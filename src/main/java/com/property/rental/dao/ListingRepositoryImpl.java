package com.property.rental.dao;

import com.property.rental.domain.Listing;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ListingRepositoryImpl implements ListingRepository {

    private Set<Listing> availableListings = new HashSet<>();

    public Optional<Listing> getListing(UUID listingId) {
        return availableListings.stream().filter(l -> l.getId().compareTo(listingId) == 0).findFirst();
    }

    public Optional<Listing> saveListing(Listing listing, boolean isNew) {
        if (!isNew) {
            availableListings.remove(listing);
        }

        if (!availableListings.add(listing)) {
            return Optional.empty();
        }
        return Optional.of(listing);
    }

    public void deleteListing(Listing listing) {
        availableListings.remove(listing);
    }
}
