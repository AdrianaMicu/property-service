package com.property.rental.controllers.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {AddressValidator.class})
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface Address {

    String message() default "Address (country code) is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
