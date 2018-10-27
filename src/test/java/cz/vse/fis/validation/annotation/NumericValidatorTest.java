package cz.vse.fis.validation.annotation;

import org.junit.Assert;
import org.junit.Test;

import cz.vse.fis.validation.NumericValidator;

public class NumericValidatorTest {
	
	@Test
	public void test() {
		Assert.assertTrue(
				new NumericValidator().isValid("0123 456 7890", null)
		);
	}
	
	@Test
	public void negativeTestWithLetters() {
		Assert.assertFalse(
				new NumericValidator().isValid("ABC DEF", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialCharacters() {
		Assert.assertFalse(
				new NumericValidator().isValid("!@#$%^&*", null)
		);
	}
	
	@Test
	public void negativeTestWithSpecialLetters() {
		Assert.assertFalse(
				new NumericValidator().isValid("ĚŠČŘŽÝÁÍÉÚŮ", null)
		);
	}

}
