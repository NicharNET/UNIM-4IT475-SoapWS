package cz.vse.fis.ws.substitution;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphanumeric;
import cz.vse.fis.validation.annotation.MorseCharacters;

/**
 * Morse code implementation
 * 
 * @author Nikolas Charalambidis
 */
@Component
@Validated
public class Morse {

	/**
	 * The input alphabet used for the Morse code
	 */
	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	/**
	 * The output alphabet used for the Morse cipher
	 */
	private static final List<String> MORSE = Arrays.asList( ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--",
			".-.-.-", "..--.." );

	/**
	 * The encryption method
	 * @param value The plaintext to encrypt
	 * @return The encrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Morse code input alphabet
	 */
	public final String encrypt(@Alphanumeric final String value) throws InvalidParameterException {
		return value.toUpperCase()
					.chars()
					.mapToObj(this::encryptCharacter)
					.collect(Collectors.joining(" "));
	}

	/**
	 * The decryption method
	 * @param value The plaintext to decrypt
	 * @return The decrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Morse code output alphabet
	 */
	public final String decrypt(@MorseCharacters final String value) throws InvalidParameterException {
		return Arrays.stream(value.toUpperCase().split("[ /]"))
					 .map(this::decryptString)
					 .map(String::valueOf)
					 .collect(Collectors.joining(""))
					 .replaceAll("//", " ");
	}
	
	/**
     * Encrypts a single character
     * @param ch The character to be encrypted
     * @return The encrypted character
     */
	private String encryptCharacter(final Integer ch) {
		String string = "/";
		if (' ' != ch) {
			int index = ALPHANUMERIC.indexOf(ch);
			if (index >= 0 && index < ALPHANUMERIC.length() ) {
				string = MORSE.get(index);
			} else throw new IllegalArgumentException("The " + ch + " is not a valid string of morse alphabet.");
		}
		return string;
	}
	
	/**
     * Decrypts a single character
     * @param ch The character to be decrypted
     * @return The decrypted character
     */
	private char decryptString(final String ch) {
		char character = '/';
		if (!"".equals(ch)) {
			int index = MORSE.indexOf(ch);
			if (index >= 0 && index < ALPHANUMERIC.length() ) {
				character =  ALPHANUMERIC.charAt(index);
			} else throw new IllegalArgumentException("The " + ch + " is not a valid string of morse alphabet.");
		}
		return character;
	}
}
