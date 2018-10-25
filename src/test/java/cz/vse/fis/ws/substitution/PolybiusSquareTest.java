package cz.vse.fis.ws.substitution;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.ws.polyalphabetic.PolybiusSquare;

public class PolybiusSquareTest {

	@Test
	public void encryptTest() {
		Assert.assertEquals(
				"41 23 35 42 15 13 25 11 12 13 14 13 15 13 21 22 23 24 23 25",
				new PolybiusSquare().encrypt("nikolas charalambidis", "charalambidis")
		);
	}
	
	@Test
	public void decryptTest() {
		Assert.assertEquals(
				"NIKOLASCHARALAMBIDIS",
				new PolybiusSquare().decrypt("41 23 35 42 15 13 25 11 12 13 14 13 15 13 21 22 23 24 23 25", "charalambidis")
		);
	}
}
