package cz.vse.fis.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cz.vse.fis.validation.AlphabeticValidator;

@Constraint(validatedBy = AlphabeticValidator.class)
@Target({ ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Numeric {

	String message() default "The input must contain only digits and spaces.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
