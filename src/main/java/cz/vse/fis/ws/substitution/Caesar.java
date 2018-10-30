package cz.vse.fis.ws.substitution;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphabetic;

/**
 * Caesar cipher implementation
 * 
 * @author Nikolas Charalambidis
 */
@Component
@Validated
public class Caesar {
	
	/**
	 * The alphabet used for the Caesar cipher
	 */
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * The default shift
	 */
	private static final int DEFAULT_SHIFT = 3;
	
	/**
	 * The encryption method
	 * @param value The plaintext to encrypt
	 * @param shift The shift index number
	 * @return The encrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Caesar cipher input alphabet
	 */
	public final String encrypt(@Alphabetic final String value, @Min(0) final Integer shift) throws InvalidParameterException {	
		return value.toUpperCase()
					.chars()
					.mapToObj(ch -> this.encryptCharacter(ch, shift))
					.map(String::valueOf)
					.collect(Collectors.joining(""));
	}
	
	/**
	 * The decryption method
	 * @param value The plaintext to decrypt
	 * @param shift The shift index number
	 * @return The decrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Caesar cipher output alphabet
	 */
	public final String decrypt(@Alphabetic final String value, @Min(0) final Integer shift) throws InvalidParameterException {
		return value.toUpperCase()
					.chars()
					.mapToObj(ch -> this.decryptCharacter(ch, shift))
					.map(String::valueOf)
					.collect(Collectors.joining(""));
	}
	
	/**
     * Encrypts a single character according the the shift index
     * @param ch The character to be encrypted
     * @param shift The shift index number
     * @return The encrypted character
     */
	private char encryptCharacter(final Integer ch, final Integer shift) {
		if (shift == null) {
			return shiftCharacter(ch, DEFAULT_SHIFT);
		} else {
			return shiftCharacter(ch, shift);
		}
	}
	
	/**
     * Decrypts a single character according the the shift index
     * @param ch The character to be decrypted
     * @param shift The shift index number
     * @return The decrypted character
     */
	private char decryptCharacter(final Integer ch, final Integer shift) {
		if (shift == null) {
			return shiftCharacter(ch, ALPHABET.length() - DEFAULT_SHIFT);
		} else {
			return shiftCharacter(ch, ALPHABET.length() - shift);
		}
	}
	
	/**
     * Shifts the character in the Caesar cipher alphabet by the index
     * @param ch The character to be shifted
     * @param shift The shift index number
     * @return The shifted character
     */
	private char shiftCharacter(final Integer ch, final int shift) {
		char character = ' ';
		if (ch != ' ') {
			int index = ALPHABET.indexOf(ch);
			if (index >= 0 && index < ALPHABET.length()) {
				character = ALPHABET.charAt((index + shift) % ALPHABET.length());
			} else throw new IllegalArgumentException("The letter " + ch + " is not a valid character to be decrypted on encrypted.");
		}
		return character;
	}
}
