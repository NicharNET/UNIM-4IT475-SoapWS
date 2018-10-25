package cz.vse.fis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.vse.fis.validation.annotation.Alphabetic;

public class AlphanumericValidator implements ConstraintValidator<Alphabetic, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.matches("[A-Za-z0-9 ]+");
	}
}
