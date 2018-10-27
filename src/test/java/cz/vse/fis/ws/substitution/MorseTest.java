package cz.vse.fis.ws.substitution;

import org.junit.Assert;
import org.junit.Test;

public class MorseTest {

	@Test
	public void encryptTest() {
		Assert.assertEquals(
				"----- .- -... -.-. .---- -.. . ..-. ..--- / ...-- --. .... .. ....-",
				new Morse().encrypt("0abc1def2 3ghi4")
		);
	}
	
	@Test
	public void decryptTest() {
		Assert.assertEquals(
				"0ABC1DEF2 3GHI4",
				new Morse().decrypt("----- .- -... -.-. .---- -.. . ..-. ..--- / ...-- --. .... .. ....-")
		);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encryptNegativeTest() {
		new Morse().encrypt("!@#$%^&*");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptNegativeTest() {
		new Morse().decrypt("!@#$%^&*");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void decryptNoMorseTest() {
		new Morse().decrypt("----- .- -... -.-.... .---- -.. . ..-. ..--- / ...-- --. .... .. ....-");
	}
}
