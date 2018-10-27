package cz.vse.fis.validation.annotation;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.validation.AlphanumericValidator;

public class AlphanumericValidatorTest {
	
	@Test
	public void test() {
		Assert.assertTrue(
				new AlphanumericValidator().isValid("DEF GZABC", null)
		);
	}
	
	@Test
	public void testWithDigits() {
		Assert.assertTrue(
				new AlphanumericValidator().isValid("0123456789", null)
		);
	}
	
	@Test
	public void testCombined() {
		Assert.assertTrue(
				new AlphanumericValidator().isValid("ABC 123", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialCharacters() {
		Assert.assertFalse(
				new AlphanumericValidator().isValid("!@#$%^&*", null)
		);
	}
}
