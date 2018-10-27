package cz.vse.fis.ws.substitution;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphanumeric;
import cz.vse.fis.validation.annotation.MorseCharacters;

@Component
@Validated
public class Morse {

	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final List<String> MORSE = Arrays.asList( ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--",
			".-.-.-", "..--.." );

	public final String encrypt(@Alphanumeric final String value) throws InvalidParameterException {
		return value.toUpperCase()
					.chars()
					.mapToObj(this::encryptCharacter)
					.collect(Collectors.joining(" "));
	}

	public final String decrypt(@MorseCharacters final String value) throws InvalidParameterException {
		return Arrays.stream(value.toUpperCase().split("[ /]"))
					 .map(this::decryptString)
					 .map(String::valueOf)
					 .collect(Collectors.joining(""))
					 .replaceAll("//", " ");
	}
	
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
