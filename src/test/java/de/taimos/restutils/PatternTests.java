package de.taimos.restutils;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class PatternTests {
	
	@Test
	public void testDate() {
		RESTAssert.assertPattern("2013-01-01", RESTAssert.PATTERN_DATE);
	}
	
	@Test
	public void testTime() {
		RESTAssert.assertPattern("12:34:56", RESTAssert.PATTERN_TIME);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testTimeFail() {
		RESTAssert.assertPattern("32:34:56", RESTAssert.PATTERN_TIME);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testTimeFail2() {
		RESTAssert.assertPattern("12:64:56", RESTAssert.PATTERN_TIME);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testTimeFail3() {
		RESTAssert.assertPattern("2:34:56", RESTAssert.PATTERN_TIME);
	}
	
	@Test
	public void testDateTime() {
		RESTAssert.assertPattern("2013-01-01 12:34:56", RESTAssert.PATTERN_DATETIME);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testDateFail() {
		RESTAssert.assertPattern("13-01-01", RESTAssert.PATTERN_DATE);
	}
	
	@Test
	public void testWord1() {
		RESTAssert.assertPattern("ABC", "A.{2}");
	}
	
	@Test(expected = WebApplicationException.class)
	public void testWord1Fail() {
		RESTAssert.assertPattern("ABCD", "A.{2}");
	}
	
	@Test(expected = WebApplicationException.class)
	public void testWord1Fail2() {
		RESTAssert.assertPattern("BCD", "A.{2}");
	}
	
}
