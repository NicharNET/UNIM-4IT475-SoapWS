package cz.vse.fis.validation.annotation;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.validation.AlphabeticValidator;

public class AlphabeticValidatorTest {
	
	@Test
	public void test() {
		Assert.assertTrue(
				new AlphabeticValidator().isValid("DEF GZABC", null)
		);
	}
		
	@Test
	public void negativeTestWithDigits() {
		Assert.assertFalse(
				new AlphabeticValidator().isValid("0123456789", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialCharacters() {
		Assert.assertFalse(
				new AlphabeticValidator().isValid("!@#$%^&*", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialLetters() {
		Assert.assertFalse(
				new AlphabeticValidator().isValid("ĚŠČŘŽÝÁÍÉÚŮ", null)
		);
	}

}
