package com.property.rental.controllers.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.property.rental.controllers.ListingRequest;
import com.property.rental.controllers.CreateListingRequest;
import com.property.rental.controllers.UpdateListingRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListingContactValidator implements ConstraintValidator<Listing, ListingRequest> {

    private PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public void initialize(Listing constraintAnnotation) {/* nothing to do */}

    @Override
    public boolean isValid(ListingRequest listingRequest, ConstraintValidatorContext context) {
        try{
            PhoneNumber phone;
            if (listingRequest instanceof CreateListingRequest) {
                phone = phoneNumberUtil.parse(listingRequest.getContact().getPhone(), ((CreateListingRequest)listingRequest).getAddress().getCountryCode());
            } else {
                phone = phoneNumberUtil.parse(listingRequest.getContact().getPhone(), ((UpdateListingRequest)listingRequest).getAddress().getCountryCode());
            }
            return phoneNumberUtil.isValidNumber(phone);
        } catch (NumberParseException nex) {
            return false;
        }
    }
}
