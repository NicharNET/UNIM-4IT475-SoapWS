package cz.vse.fis.ws.substitution;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphabetic;

@Component
@Validated
public class Caesar {
	
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public final String encrypt(@Alphabetic final String value, @Min(0) final Integer shift) throws InvalidParameterException {
		
		return value.toUpperCase()
					.chars()
					.mapToObj(ch -> this.encryptCharacter(ch, shift))
					.map(String::valueOf)
					.collect(Collectors.joining(""));
	}
	
	public final String decrypt(@Alphabetic final String value, @Min(0) final Integer shift) throws InvalidParameterException {
		
		return value.toUpperCase()
					.chars()
					.mapToObj(ch -> this.decryptCharacter(ch, shift))
					.map(String::valueOf)
					.collect(Collectors.joining(""));
	}
	
	private char encryptCharacter(final Integer ch, final Integer shift) {
		if (shift == null) {
			return shiftCharacter(ch, 3);
		} else {
			return shiftCharacter(ch, shift);
		}
	}
	
	private char decryptCharacter(final Integer ch, final Integer shift) {
		if (shift == null) {
			return shiftCharacter(ch, 23);
		} else {
			return shiftCharacter(ch, 26 - shift);
		}
	}
	
	private char shiftCharacter(final Integer ch, final int shift) {
		char character = ' ';
		if (ch != ' ') {
			int index = ALPHABET.indexOf(ch);
			if (index >= 0 && index < ALPHABET.length()) {
				character = ALPHABET.charAt((index + shift) % 26);
			} else throw new IllegalArgumentException("The letter " + ch + " is not a valid character to be decrypted on encrypted.");
		}
		return character;
	}
}
