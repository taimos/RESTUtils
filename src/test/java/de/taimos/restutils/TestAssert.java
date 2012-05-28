package de.taimos.restutils;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;

/**
 * @author thoeger
 * 
 */
@SuppressWarnings("javadoc")
public class TestAssert {

	@Test()
	public void testBooleanPass() {
		RESTAssert.assertFalse(false);
		RESTAssert.assertTrue(true);
		RESTAssert.assertTrue(new Object() != null);
	}

	@Test(expected = WebApplicationException.class)
	public void testBooleanFail1() {
		RESTAssert.assertTrue(false);
	}

	@Test(expected = WebApplicationException.class)
	public void testBooleanFail2() {
		RESTAssert.assertTrue(new Object() == null);
	}

	@Test
	public void testNotNull() {
		RESTAssert.assertNotNull(new Object());
	}

	@Test(expected = WebApplicationException.class)
	public void testNotNullFail() {
		RESTAssert.assertNotNull(null);
	}

	@Test
	public void testStringNotEmpty() {
		RESTAssert.assertNotEmpty("Hello");
	}

	@Test(expected = WebApplicationException.class)
	public void testStringNotEmptyFailNull() {
		RESTAssert.assertNotEmpty((String)null);
	}

	@Test(expected = WebApplicationException.class)
	public void testStringNotEmptyFailEmpty() {
		RESTAssert.assertNotEmpty("");
	}

	@Test
	public void testCollectionNotEmpty() {
		final List<Object> list = new ArrayList<>();
		list.add(new Object());
		RESTAssert.assertNotEmpty(list);
	}

	@Test(expected = WebApplicationException.class)
	public void testCollectionNotEmptyFail() {
		final List<Object> list = new ArrayList<>();
		RESTAssert.assertNotEmpty(list);
	}

	@Test
	public void testListSingle() {
		final List<Object> list = new ArrayList<>();
		list.add(new Object());
		RESTAssert.assertSingleElement(list);
	}

	@Test(expected = WebApplicationException.class)
	public void testListSingleFailEmpty() {
		final List<Object> list = new ArrayList<>();
		RESTAssert.assertSingleElement(list);
	}

	@Test(expected = WebApplicationException.class)
	public void testListSingleFailMultiple() {
		final List<Object> list = new ArrayList<>();
		list.add(new Object());
		list.add(new Object());
		RESTAssert.assertSingleElement(list);
	}

	@Test
	public void testEquals() {
		RESTAssert.assertEquals("Foo", "Foo");
	}
}
