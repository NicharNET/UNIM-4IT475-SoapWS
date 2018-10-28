package cz.vse.fis.ws.substitution;

import cz.vse.fis.validation.annotation.Alphabetic;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;

/**
 * Created by Alexandra Kolpakova on 27.10.2018.
 */

@Component
@Validated
public class Augustus {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public final String encrypt(@Alphabetic final String value) throws InvalidParameterException {

        return value.toUpperCase()
                .chars()
                .mapToObj(ch -> this.encryptCharacter(ch))
                .map(String::valueOf)
                .collect(Collectors.joining(""))
                .replaceAll("A", "AA");
    }

    public final String decrypt(@Alphabetic final String value) throws InvalidParameterException {

        return value.toUpperCase()
                .replaceAll("AA", "A")
                .chars()
                .mapToObj(ch -> this.decryptCharacter(ch))
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private char encryptCharacter(final Integer ch) {
        return shiftCharacter(ch, 1);
    }

    private char decryptCharacter(final Integer ch) {
        return shiftCharacter(ch, 25);
    }

    private char shiftCharacter(final Integer ch, final int shift) {
        char character = ' ';
        if (ch != ' ') {
            int index = ALPHABET.indexOf(ch);
            if (index >= 0 && index < ALPHABET.length()) {
                character = ALPHABET.charAt((index + shift) % 26);
            } else
                throw new IllegalArgumentException("The letter " + ch + " is not a valid character to be decrypted on encrypted.");
        }
        return character;
    }
}
