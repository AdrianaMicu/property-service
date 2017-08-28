package com.property.rental.domain;

import java.util.Objects;

public class Contact {

    private String phone;
    private String formattedPhone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(formattedPhone, contact.formattedPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formattedPhone);
    }
}
