package cz.vse.fis.ws.substitution;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.ws.substitution.Caesar;

public class CaesarTest {
	
	@Test
	public void encryptTest() {
		Assert.assertEquals(
				"DEF GZABC",
				new Caesar().encrypt("Abc dwxyz", 3)
		);
	}
	
	@Test
	public void decryptTest() {
		Assert.assertEquals(
				"ABC DWXYZ",
				new Caesar().decrypt("Def gzabc", 3)
		);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encryptNegativeTest() {
		new Caesar().encrypt("!@#$%^&*", 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptNegativeTest() {
		new Caesar().decrypt("!@#$%^&*", 3);
	}
}
