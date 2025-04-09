package com.data.toan.util.EnumRegex;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TypeValidator.class)
public @interface TypeEnumCheck {
    String name();

    String regexp();

    String message() default "{name} must match {regexp}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
