package cz.vse.fis.ws.polyalphabetic;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphabetic;
import cz.vse.fis.validation.annotation.Numeric;

/**
 * Polybius Square cipher implementation
 * 
 * @author Nikolas Charalambidis
 */
@Component
@Validated
public class PolybiusSquare {
	
	/**
	 * The alphabet used for the Polybius Square cipher
	 */
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
	
	/**
	 * The row and column fixed size
	 */
	private static final int size = 5;

	/**
	 * The encryption method
	 * @param value The plaintext to encrypt
	 * @param key The key for the Polybius Square cipher
	 * @return The encrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Polybius Square cipher input alphabet
	 */
	public final String encrypt(@Alphabetic final String value, @Alphabetic final String key) throws InvalidParameterException {
		
		StringBuilder sb = new StringBuilder();
		
		String[][] square = this.constructSquare(key);
		String[] array = value.toUpperCase().replaceAll("X", "Y").replaceAll(" ", "").split("");
		
		boolean found = false;
		String letter = null;
		for (String string: array) {
			String number = "";
			for (int i=0; i<square.length; i++) {
				String[] subArray = square[i];
				for (int j=0; j<subArray.length; j++) {
					letter = subArray[j];
					if (string.equals(letter)) {
						int ii = i+1;
						int jj = j+1;
						number = new StringBuilder(String.valueOf(ii)).append(jj).toString();
						found = true;
						break;
					}
				}
			}
			sb.append(number).append(" ");
		}
	
		if (!found) {
			throw new IllegalArgumentException("The letter " + letter + " is not a valid character to be decrypted on encrypted.");
		}
		return sb.toString().trim();
	}

	/**
	 * The decryption method
	 * @param value The plaintext to decrypt
	 * @param key The key for the Polybius Square cipher
	 * @return The decrypted plaintext
	 * @throws InvalidParameterException The exception is thrown in case the character is not found in the Polybius Square cipher output alphabet
	 */
	public final String decrypt(@Numeric final String value, @Alphabetic final String key) throws InvalidParameterException {

		String[][] square = this.constructSquare(key);
		return Arrays.asList(value.split(" "))
					 .stream()
					 .map(s -> {
						 int i = Integer.parseInt(s.charAt(0) + "") - 1;
						 int j = Integer.parseInt(s.charAt(1) + "") - 1;
						 return square[i][j];
					 }).collect(Collectors.joining(""));
	}
	
	/**
	 * Constructs the Polybius Square according to the passed key
	 * @param key The key for the Polybius Square cipher
	 * @return The array of letters representing the Polybius Square
	 */
	private String[][] constructSquare(String key) {
		
		String[][] square = new String[5][5];
		int inner = 0;
		int outer = 0;
		
		List<String> keyDuplicateLetters = Arrays.asList(key.toUpperCase().replace(" ", "").split(""));		
		List<String> letters = new ArrayList<>(new LinkedHashSet<>(keyDuplicateLetters));
		List<String> remainingLetters = new ArrayList<>(Arrays.asList(ALPHABET.split("")));
		remainingLetters.removeAll(letters);
		letters.addAll(remainingLetters);

		Iterator<String> iterator = letters.iterator();
		while (iterator.hasNext()) {
			if (outer < size) {
				if (inner < size) { 
					square[outer][inner] = iterator.next();
					inner++;
				} else {
					inner = 0;
					outer++;
				}
			} else {
				break;
			}
		}
		return square;
	}
}
