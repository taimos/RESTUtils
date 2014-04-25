package de.taimos.restutils;

/*
 * #%L Taimos RESTUtils %% Copyright (C) 2011 - 2013 Taimos GmbH %% Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License. #L%
 */

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;

/**
 * @author thoeger
 * 
 */
@SuppressWarnings({"javadoc"})
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
		RESTAssert.assertNotEmpty((String) null);
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
	
	@Test
	public void testEqualsNull() {
		RESTAssert.assertEquals(null, null);
	}
	
	@Test
	public void testInt() {
		RESTAssert.assertInt("123");
	}
	
	@Test
	public void testIntPlus() {
		RESTAssert.assertInt("+123");
	}
	
	@Test
	public void testIntNegative() {
		RESTAssert.assertInt("-123");
	}
	
	@Test(expected = WebApplicationException.class)
	public void testIntFail() {
		RESTAssert.assertInt("Foo");
	}
}
