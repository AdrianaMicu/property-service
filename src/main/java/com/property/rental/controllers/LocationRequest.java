package com.property.rental.controllers;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LocationRequest {

    protected static final String LATITUDE_ERROR_MSG = "A valid Latitude is required";
    protected static final String LONGITUDE_ERROR_MSG = "A valid Longitude is required";

    @Max(value = 90, message = LATITUDE_ERROR_MSG)
    @Min(value = -90, message = LATITUDE_ERROR_MSG)
    private BigDecimal lat;

    @Max(value = 180, message = LONGITUDE_ERROR_MSG)
    @Min(value = -180, message = LONGITUDE_ERROR_MSG)
    private BigDecimal lng;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }
}
