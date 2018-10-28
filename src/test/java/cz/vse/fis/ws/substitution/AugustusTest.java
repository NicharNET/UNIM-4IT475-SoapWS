package cz.vse.fis.ws.substitution;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexandra Kolpakova on 27.10.2018.
 */
public class AugustusTest {

    @Test
    public void encryptTest() {
        Assert.assertEquals(
                "BCD EXYAAZAA",
                new Augustus().encrypt("Abc dwxZyz")
        );
    }

    @Test
    public void decryptTest() {
        Assert.assertEquals(
                "SDF AZEW",
                new Augustus().decrypt("TEG BAAFX")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void encryptNegativeTest() {
        new Augustus().encrypt("!@#$%^&*");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decryptNegativeTest() {
        new Augustus().decrypt("!@#$%^&*");
    }
}
