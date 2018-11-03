package cz.vse.fis.ws.substitution;

import cz.vse.fis.validation.annotation.Alphabetic;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;

/**
 * Augustus cipher implementation
 * 
 * @author Alexandra Kolpakova
 */
@Component
@Validated
public class Augustus {

	/**
	 * The alphabet used for the cipher
	 */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The default shift
     */
    private static final int DEFAULT_SHIFT = 1;
	/**
	 * The encryption method
	 * @param value The plaintext to encrypt
	 * @return The encrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Augustus cipher input alphabet
	 */
    public final String encrypt(@Alphabetic final String value) throws InvalidParameterException {

        return value.toUpperCase()
                .chars()
                .mapToObj(ch -> this.encryptCharacter(ch))
                .map(String::valueOf)
                .collect(Collectors.joining(""))
                .replaceAll("A", "AA");
    }

    /**
	 * The decryption method
	 * @param value The plaintext to decrypt
	 * @return The decrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Augustus cipher output alphabet
	 */
    public final String decrypt(@Alphabetic final String value) throws InvalidParameterException {

        return value.toUpperCase()
                .replaceAll("AA", "A")
                .chars()
                .mapToObj(ch -> this.decryptCharacter(ch))
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    /**
     * Encrypts a single character
     * @param ch The character to be encrypted
     * @return The encrypted character
     */
    private char encryptCharacter(final Integer ch) {
        return shiftCharacter(ch, DEFAULT_SHIFT);
    }

    /**
     * Decrypts a single character
     * @param ch The character to be decrypted
     * @return The decrypted character
     */
    private char decryptCharacter(final Integer ch) {
        return shiftCharacter(ch, ALPHABET.length() - DEFAULT_SHIFT);
    }

    /**
     * Shifts the character in the Augustus cipher alphabet by the index
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
            } else
                throw new IllegalArgumentException("The letter " + ch + " is not a valid character to be decrypted on encrypted.");
        }
        return character;
    }
}
