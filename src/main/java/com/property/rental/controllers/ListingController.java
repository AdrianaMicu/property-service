package com.property.rental.controllers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.property.rental.domain.Listing;
import com.property.rental.mappers.ListingMapper;
import com.property.rental.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/")
public class ListingController {

    private static final String LISTINGS_URL = "listings";

    @Autowired
    private ListingService listingService;

    @Autowired
    private ListingMapper listingMapper;

    @RequestMapping(value = LISTINGS_URL + "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Listing getListing(@PathVariable("id") UUID listingId) {
        return  listingService.getListing(listingId);
    }

    @RequestMapping(value = LISTINGS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Listing createListing(@Valid @RequestBody CreateListingRequest createListingRequest) throws NumberParseException {
        return listingService.createListing(listingMapper.toListing(createListingRequest));
    }

    @RequestMapping(value = LISTINGS_URL + "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Listing updateListing(@PathVariable("id") UUID listingId,
                                 @Valid @RequestBody UpdateListingRequest updateListingRequest) throws NumberParseException {
        return listingService.updateListing(listingId, listingMapper.toListing(updateListingRequest));
    }

    @RequestMapping(value = LISTINGS_URL + "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteListing(@PathVariable("id") UUID listingId) {
        listingService.deleteListing(listingId);
    }

}
