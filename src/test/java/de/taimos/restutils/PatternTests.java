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
	
	// ISO Date
	
	@Test
	public void testISO1() {
		RESTAssert.assertPattern("2013-01-01T12:34:56", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO2() {
		RESTAssert.assertPattern("2013-01-01", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO3() {
		RESTAssert.assertPattern("2013-01-01T12:34:56Z", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO4() {
		RESTAssert.assertPattern("2013-01-01T12:34:56+01:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO5() {
		RESTAssert.assertPattern("2013-01-01T12:34:56-10:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO6() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.123", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO7() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.123-10:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO8() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.12", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO9() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.1-10:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test
	public void testISO10() {
		RESTAssert.assertPattern("2013-01-01T12:34:56-1000", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail1() {
		RESTAssert.assertPattern("2013-01-01T12:34:56-1:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail2() {
		RESTAssert.assertPattern("2013-01-01T12:34:561:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail3() {
		RESTAssert.assertPattern("2013-01-01T12:34:56H", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail4() {
		RESTAssert.assertPattern("2013-01-01T12:34:56Z+01:00", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail5() {
		RESTAssert.assertPattern("2013-01-01 12:34:56", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail6() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.", RESTAssert.PATTERN_ISO);
	}
	
	@Test(expected = WebApplicationException.class)
	public void testISOFail7() {
		RESTAssert.assertPattern("2013-01-01T12:34:56.1234", RESTAssert.PATTERN_ISO);
	}
	
}
