package cz.vse.fis.ws.polyalphabetic;

import org.junit.Assert;
import org.junit.Test;

public class GronsfeldTest {

	@Test
	public void encryptTest() {
		Assert.assertEquals(
				"HTRRTHHPE",
				new Gronsfeld().encrypt("Gronsfeld", "12 34")
		);
	}
	
	@Test
	public void decryptTest() {
		Assert.assertEquals(
				"GRONSFELD",
				new Gronsfeld().decrypt("Htrrthhpe", "1 23 4")
		);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encryptInvalidCharactersTest() {
		new Gronsfeld().encrypt("!@#$%^&*", "123");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptInvalidCharactersTest() {
		new Gronsfeld().decrypt("!@#$%^&*", "123");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encryptInvalidKeyTest() {
		new Gronsfeld().encrypt("abc", "!@#$%^&*");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptInvalidKeyTest() {
		new Gronsfeld().decrypt("abc", "!@#$%^&*");
	}
}
