package edu.hackeru.evgenyzakalinsky.mytstsql.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) //field - var
@Retention(RetentionPolicy.RUNTIME) //we need to check in runtime
@Constraint(validatedBy = {/*class for validator*/})
public @interface UniqueEmail {
    String message() default "Email address must be unique";

    //enable validations groups
    Class<?>[] groups() default {};

    //the annotation stores a payload
    //the field value is stored in the payload
    Class<? extends Payload>[] payload() default {};
}
