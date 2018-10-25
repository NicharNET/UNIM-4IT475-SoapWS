package cz.vse.fis.ws.substitution;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cz.vse.fis.validation.annotation.Alphanumeric;

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

	public final String decrypt(@Alphanumeric final String value) throws InvalidParameterException {
		return Arrays.stream(value.toUpperCase().split("[ /]"))
					 .map(this::decryptString)
					 .map(String::valueOf)
					 .collect(Collectors.joining(""))
					 .replaceAll("//", " ");
	}
	
	private String encryptCharacter(final Integer ch) {
		return ch == ' ' ? "/" : MORSE.get(ALPHANUMERIC.indexOf(ch));
	}
	
	private char decryptString(final String ch) {
		return "".equals(ch) ? '/' : ALPHANUMERIC.charAt(MORSE.indexOf(ch));
	}
}
