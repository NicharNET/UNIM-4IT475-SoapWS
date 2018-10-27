package cz.vse.fis.validation.annotation;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.validation.MorseCharactersValidator;

public class MorseCharactersValidatorTest {
	
	@Test
	public void test() {
		Assert.assertTrue(
				new MorseCharactersValidator().isValid("..-", null)
		);
	}
	
	@Test
	public void testWithSpaces() {
		Assert.assertTrue(
				new MorseCharactersValidator().isValid("..- / --.", null)
		);
	}
	
	@Test
	public void negativeTestWithLetters() {
		Assert.assertFalse(
				new MorseCharactersValidator().isValid("ABC DEF", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialCharacters() {
		Assert.assertFalse(
				new MorseCharactersValidator().isValid("!@#$%^&*", null)
		);
	}
}
