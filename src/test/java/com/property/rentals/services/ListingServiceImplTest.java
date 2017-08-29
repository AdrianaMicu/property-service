package com.property.rentals.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.property.rental.dao.ListingRepository;
import com.property.rental.domain.Address;
import com.property.rental.domain.Contact;
import com.property.rental.domain.Listing;
import com.property.rental.domain.Location;
import com.property.rental.exceptions.ListingException;
import com.property.rental.services.ListingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ListingServiceImplTest {

    @InjectMocks
    private ListingServiceImpl listingService;

    @Mock
    private ListingRepository listingRepo;
    
    @Captor
    private ArgumentCaptor<Listing> listingCaptor;

    private UUID uuid = UUID.randomUUID();

    @Test
    public void getListing_shouldSucceed() {
        Listing savedListing = mock(Listing.class);
        when(savedListing.getId()).thenReturn(uuid);

        when(listingRepo.getListing(uuid)).thenReturn(Optional.of(savedListing));

        Listing retrievedListing = listingService.getListing(uuid);

        assertThat(retrievedListing).isNotNull();
        assertEquals(retrievedListing.getId(), uuid);
    }

    @Test
    public void getListing_shouldThrowException() {
        when(listingRepo.getListing(uuid)).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> listingService.getListing(uuid));

        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(ListingException.class);
        assertEquals("Could not find any listing with id " + uuid, throwable.getMessage());
    }

    @Test
    public void createListing_shouldSucceed() {
        Listing listing = new Listing();

        when(listingRepo.saveListing(listing, true)).thenReturn(Optional.of(mock(Listing.class)));
        
        listingService.createListing(listing);
        
        verify(listingRepo).saveListing(listingCaptor.capture(), eq(true));

        Listing listingToSave = listingCaptor.getValue();
        assertThat(listingToSave).isNotNull();
        assertThat(listingToSave.getId()).isNotNull();
    }
    
    @Test
    public void createListing_shouldThrowException() {
    	Listing listing = mock(Listing.class);
    	when(listingRepo.saveListing(listing, true)).thenReturn(Optional.empty());
    	
        Throwable throwable = catchThrowable(() -> listingService.createListing(listing));

        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(ListingException.class);
        assertEquals("Listing with this data already exists", throwable.getMessage());
    }
    
    @Test
    public void updateListing_shouldSucceed() {
    	Contact originalContact = new Contact();
    	originalContact.setPhone("4407123456789");
    	originalContact.setFormattedPhone("+44 7123 456789");
    	String originalAddressString = "1 Long Street";
    	String originalPostalCodeString = "aa11aa";
    	Address originalAddress = new Address();
    	originalAddress.setAddress(originalAddressString);
    	originalAddress.setPostalCode(originalPostalCodeString);
    	originalAddress.setCountryCode("GB");
    	originalAddress.setCity("London");
    	originalAddress.setCountry("United Kingdom");
    	String originalLatString = "51.522338";
    	String originalLngString = "-0.103168";
    	Location originalLocation = new Location();
    	originalLocation.setLat(new BigDecimal(originalLatString));
    	originalLocation.setLng(new BigDecimal(originalLngString));
    	Listing listing = new Listing();
    	listing.setId(uuid);
    	listing.setAddress(originalAddress);
    	listing.setContact(originalContact);
    	listing.setLocation(originalLocation);
    	
    	when(listingRepo.getListing(uuid)).thenReturn(Optional.of(listing));
    	
    	Address addressToUpdate = new Address();
    	addressToUpdate.setAddress("2 Long Street");
    	addressToUpdate.setPostalCode("aa12aa");
    	Location locationToUpdate = new Location();
    	locationToUpdate.setLat(new BigDecimal("51.530106"));
    	locationToUpdate.setLng(new BigDecimal("-0.127716"));
    	Listing listingToUpdate = new Listing();
    	listingToUpdate.setAddress(addressToUpdate);
    	listingToUpdate.setLocation(locationToUpdate);
    	
    	when(listingRepo.saveListing(listing, false)).thenReturn(Optional.of(mock(Listing.class)));
    	
    	listingService.updateListing(uuid, listingToUpdate);
    	
    	verify(listingRepo).saveListing(listingCaptor.capture(), eq(false));
    	
    	Listing updatedListing = listingCaptor.getValue();
    	assertThat(updatedListing).isNotNull();
    	assertEquals(updatedListing.getId(), uuid);
    	assertEquals(updatedListing.getContact(), originalContact);
    	assertEquals(updatedListing.getAddress().getAddress(), listingToUpdate.getAddress().getAddress());
    	assertEquals(updatedListing.getAddress().getPostalCode(), listingToUpdate.getAddress().getPostalCode());
    	assertNotEquals(updatedListing.getAddress().getPostalCode(), originalPostalCodeString);
    	assertNotEquals(updatedListing.getAddress().getAddress(), originalAddressString);
    	assertEquals(updatedListing.getAddress().getCity(), originalAddress.getCity());
    	assertEquals(updatedListing.getAddress().getCountryCode(), originalAddress.getCountryCode());
    	assertNotEquals(updatedListing.getLocation().getLat(), originalLatString);
    	assertNotEquals(updatedListing.getLocation().getLat(), originalLngString);
    }
}
