package com.property.rental.controllers.util;

import com.property.rental.controllers.AddressRequest;
import com.property.rental.controllers.CreateAddressRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AddressValidator implements ConstraintValidator<Address, AddressRequest> {

    private static final List<String> countryCodes = Arrays.asList(Locale.getISOCountries());

    @Override
    public void initialize(Address constraintAnnotation) {/* nothing to do */}

    @Override
    public boolean isValid(AddressRequest addressRequest, ConstraintValidatorContext context) {
        return countryCodes.contains(addressRequest.getCountryCode());
    }
}
