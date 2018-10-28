package cz.vse.fis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.vse.fis.validation.annotation.Alphanumeric;

/**
 * Alphanumeric characters validator
 * 
 * @author Nikolas Charalambidis
 */
public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.matches("[A-Za-z0-9 ]+");
	}
}
