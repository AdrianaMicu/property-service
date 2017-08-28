package com.property.rental.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Location {

    private BigDecimal lat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lat, location.lat) &&
                Objects.equals(lng, location.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
