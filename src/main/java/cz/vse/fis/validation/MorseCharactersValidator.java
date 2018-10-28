package cz.vse.fis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.vse.fis.validation.annotation.MorseCharacters;

/**
 * Morse alphabet characters validator
 * 
 * @author Nikolas Charalambidis
 */
public class MorseCharactersValidator implements ConstraintValidator<MorseCharacters, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.matches("[\\.\\-/ ]+");
	}
}
