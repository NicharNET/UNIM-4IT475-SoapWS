package cz.vse.fis.ws.polyalphabetic;

import cz.vse.fis.validation.annotation.Alphabetic;
import cz.vse.fis.validation.annotation.Numeric;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Vigenere cipher implementation
 * <p>
 * Created by Alexandra Kolpakova on 03.11.2018.
 */

@Component
@Validated
public class Vigenere {

    /**
     * The alphabet used for the Vigenere cipher
     */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The encryption method
     *
     * @param text The plaintext to encrypt
     * @param key  The key for the Vigenere cipher
     * @return The encrypted plaintext
     * @throws InvalidParameterException The exception is thrown in case the character is not found in the Vigenere cipher input alphabet
     */
    public final String encrypt(@Alphabetic String text, @Alphabetic String key) throws InvalidParameterException {
        String res = "";
        text = text.toUpperCase();
        key = prepareKey(text, key);
        String[][] square = this.constructSquare();
        for (int x = 0; x < text.length(); x++) {
            if (ALPHABET.contains(String.valueOf(text.charAt(x)))) {
                int pos1 = ALPHABET.indexOf(text.charAt(x));
                int pos2 = ALPHABET.indexOf(key.charAt(x));
                res += square[pos1][pos2];
            } else {
                res += text.charAt(x);
            }
        }
        return res;
    }

    /**
     * The decryption method
     *
     * @param text The plaintext to decrypt
     * @param key  The key for the Vigenere cipher
     * @return The decrypted plaintext
     * @throws InvalidParameterException The exception is thrown in case the character is not found in the Vigenere cipher output alphabet
     */
    public final String decrypt(@Numeric String text, @Alphabetic String key) throws InvalidParameterException {
        String res = "";
        text = text.toUpperCase();
        key = prepareKey(text, key);
        String[][] square = constructSquare();
        for (int x = 0; x < text.length(); x++) {
            if (ALPHABET.contains(String.valueOf(text.charAt(x)))) {
                int pos1 = ALPHABET.indexOf(key.charAt(x));
                for (int i = 0; i < square.length; i++) {
                    if (square[pos1][i].equals((String.valueOf(text.charAt(x))))) {
                        res += ALPHABET.charAt(i);
                        continue;
                    }
                }
            } else {
                res += text.charAt(x);
            }
        }
        return res;
    }

    /**
     * Prepares key for Vigenere cipher
     *
     * @param text The text for the Vigenere cipher
     * @param key  The key for the Vigenere cipher
     * @return The key string with whitespaces and non-alphabetical string values
     */
    public static String prepareKey(String text, String key) {
        int msgLength = text.length();
        int keyLength = key.length();
        if (msgLength > keyLength) {
            int count = msgLength / keyLength;
            StringBuilder builder = new StringBuilder(key);
            for (int iDx = 0; iDx < count; iDx++) {
                builder.append(key);
            }
            key = builder.toString();
        }
        List<Integer> positions = new ArrayList();
        List<Character> value = new ArrayList();
        for (int i = 0; i < text.length(); i++) {
            if (String.valueOf(text.charAt(i)).matches("[^A-Za-z]+")) {
                positions.add(i);
                value.add(text.charAt(i));
            }
        }

        StringBuilder builder1 = new StringBuilder(key);
        for (int pos : positions) {
            builder1.insert(pos, value.get(positions.indexOf(pos)));
        }
        key = builder1.toString().toUpperCase();
        return key;
    }

    /**
     * Constructs the Vigenere square
     *
     * @return The array of letters representing Vigenere square
     */
    private String[][] constructSquare() {
        String[][] square = new String[26][26];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                int res = j + i;
                if (res > 25) {
                    res = res - 26;
                }
                square[i][j] = String.valueOf(ALPHABET.charAt(res));
            }
        }
        return square;
    }
}
