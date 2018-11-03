package cz.vse.fis.ws.polyalphabetic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexandra Kolpakova on 03.11.2018.
 */
public class VigenereTest {

    @Test
    public void encryptTest() {
        Assert.assertEquals(
                "OPOW VW R XZAZ ;)",
                new Vigenere().encrypt("This is a test ;)", "vigenere")
        );
    }

    @Test
    public void decryptTest() {
        Assert.assertEquals(
                "THIS IS A TEST ;)",
                new Vigenere().decrypt("OPOW VW R XZAZ ;)", "vigenere")
        );
    }

}
