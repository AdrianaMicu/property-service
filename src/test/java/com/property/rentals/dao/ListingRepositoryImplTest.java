package com.property.rentals.dao;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.property.rental.dao.ListingRepositoryImpl;
import com.property.rental.domain.Address;
import com.property.rental.domain.Contact;
import com.property.rental.domain.Listing;
import com.property.rental.domain.Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ListingRepositoryImplTest {

	@InjectMocks
	private ListingRepositoryImpl listingRepo;
	
	private Listing listing1;
	private Listing listing2;
	
	private UUID uuid1;
	
	@Before
	public void setup() {
		Contact contact1 = new Contact();
		contact1.setPhone("4407123456789");
		contact1.setFormattedPhone("+44 7123 456789");
    	Address address1 = new Address();
    	address1.setAddress("1 Long Street");
    	address1.setPostalCode("aa11aa");
    	address1.setCountryCode("GB");
    	address1.setCity("London");
    	address1.setCountry("United Kingdom");
    	Location location1 = new Location();
    	location1.setLat(new BigDecimal("51.522338"));
    	location1.setLng(new BigDecimal("-0.103168"));
    	uuid1 = UUID.randomUUID();
    	listing1 = new Listing();
    	listing1.setId(uuid1);
    	listing1.setAddress(address1);
    	listing1.setContact(contact1);
    	listing1.setLocation(location1);
    	
    	listing2 = new Listing();
    	listing2.setId(UUID.randomUUID());
    	listing2.setAddress(address1);
    	listing2.setContact(contact1);
    	listing2.setLocation(location1);
	}
	
	@Test
	public void getListing_shouldSucceed() {
		listingRepo.saveListing(listing1, true);
		
		Listing listing = listingRepo.getListing(uuid1).get();
		
		assertEquals(listing1, listing);
	}
	
	@Test
	public void saveListing_shouldRetrunEmpltyOptionalOnDuplicate() {
		Optional<Listing> listingOK = listingRepo.saveListing(listing1, true);
		Optional<Listing> listingEmpty = listingRepo.saveListing(listing2, true);
		
		assertTrue(listingOK.isPresent());
		assertFalse(listingEmpty.isPresent());
	}
}
