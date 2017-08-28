package com.property.rental.controllers;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateLocationRequest extends LocationRequest {

    @NotNull(message = LATITUDE_ERROR_MSG)
    public BigDecimal getLat() {
        return super.getLat();
    }

    @NotNull(message = LONGITUDE_ERROR_MSG)
    public BigDecimal getLng() {
        return super.getLng();
    }
}
