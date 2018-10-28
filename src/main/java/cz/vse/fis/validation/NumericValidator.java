package cz.vse.fis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.vse.fis.validation.annotation.Numeric;

/**
 * Numeric characters validator
 * 
 * @author Nikolas Charalambidis
 */
public class NumericValidator implements ConstraintValidator<Numeric, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.matches("[0-9 ]+");
	}
}
