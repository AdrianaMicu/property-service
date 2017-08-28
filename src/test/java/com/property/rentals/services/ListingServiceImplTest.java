package com.property.rentals.services;

import com.property.rental.dao.ListingRepository;
import com.property.rental.domain.Listing;
import com.property.rental.exceptions.ListingException;
import com.property.rental.services.ListingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListingServiceImplTest {

    @InjectMocks
    private ListingServiceImpl listingService;

    @Mock
    private ListingRepository listingRepo;

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
        Listing listing = mock(Listing.class);

        assertThat(listing.getId()).isNull();

        Listing created = listingService.createListing(listing);

        assertThat(created).isNotNull();
        assertEquals(created.getId(), uuid);
    }
}
