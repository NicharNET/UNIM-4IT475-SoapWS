package cz.vse.fis.ws.polyalphabetic;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphabetic;
import cz.vse.fis.validation.annotation.Numeric;

/**
 * Gronsfeld cipher implementation
 * 
 * @author Nikolas Charalambidis
 */
@Component
@Validated
public class Gronsfeld {

	/**
	 * The alphabet used for the Gronsfeld cipher
	 */
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWYZ";

	/**
	 * The encryption method
	 * @param value The plaintext to encrypt
	 * @param key The key for the Gronsfeld cipher
	 * @return The encrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Gronsfeld cipher input alphabet
	 */
	public final String encrypt(@Alphabetic String value, @Numeric String key) {
		String upperCaseValue = value.toUpperCase();
		String trimmedKey = key.replace(" ", "");
		return IntStream.range(0, value.length())
				 .mapToObj(i -> this.shiftCharacter(upperCaseValue.charAt(i), this.cleanKeyIndex(trimmedKey, i)))
				 .map(Object::toString)
				 .collect(Collectors.joining(""));
	}

	/**
	 * The decryption method
	 * @param value The plaintext to decrypt
	 * @param key The key for the Gronsfeld cipher
	 * @return The decrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the  Gronsfeld cipher output alphabet
	 */
	public final String decrypt(@Alphabetic String value, @Numeric String key) {
		String upperCaseValue = value.toUpperCase();
		String trimmedKey = key.replace(" ", "");
		return IntStream.range(0, value.length())
				 .mapToObj(i -> this.shiftCharacter(upperCaseValue.charAt(i), ALPHABET.length() - this.cleanKeyIndex(trimmedKey, i)))
				 .map(Object::toString)
				 .collect(Collectors.joining(""));
	}

	/**
	 * Shifts the character in the Caesar cipher alphabet by the index
	 * @param ch The character to be shifted
	 * @param shift The shift index number
	 * @return The shifted character
	 */
	private char shiftCharacter(final char ch, final int shift) {
		char character = ' ';
		if (ch != ' ') {
			int index = ALPHABET.indexOf(ch);
			if (index >= 0 && index < ALPHABET.length()) {
				character = ALPHABET.charAt((index + shift) % ALPHABET.length());
			} else
				throw new IllegalArgumentException(
						"The letter " + ch + " is not a valid character to be decrypted on encrypted.");
		}
		return character;
	}
	
	private int cleanKeyIndex(String key, int index) {
		int mod = index % key.length();
		return Integer.parseInt(key.substring(mod, mod + 1));
	}
}
