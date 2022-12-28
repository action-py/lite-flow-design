package com.example.demovalidate.annotation;

import com.example.demovalidate.validator.OfferFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR})
@Constraint(validatedBy = {OfferFormatValidator.class})
public @interface OfferFormat {
    String message() default "";
    Class< ? >[] groups() default {};
    Class< ? extends Payload>[] payload() default { };
}
