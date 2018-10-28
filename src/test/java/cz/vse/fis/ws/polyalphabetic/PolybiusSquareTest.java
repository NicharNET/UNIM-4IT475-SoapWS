package cz.vse.fis.ws.polyalphabetic;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.ws.polyalphabetic.PolybiusSquare;

public class PolybiusSquareTest {

	@Test
	public void encryptTest() {
		Assert.assertEquals(
				"31 23 35 35 12 53 12 44 35 22",
				new PolybiusSquare().encrypt("hello world", "soap")
		);
	}
	
	@Test
	public void decryptTest() {
		new PolybiusSquare().decrypt("31 23 35 35 12 53 12 44 35 22", "soap");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encryptNegativeTest() {
		new PolybiusSquare().encrypt("!@#$%^&*", "soap");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptNegativeTest() {
		new PolybiusSquare().decrypt("!@#$%^&*", "soap");
	}
}
