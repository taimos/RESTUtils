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

import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

/**
 * Use this class to assert preconditions in REST Handler<br>
 * <br>
 * assert methods throw {@link WebApplicationException} on failure
 * 
 * @author thoeger
 * 		
 */
public final class RESTAssert {
	
	private static final StatusType UNPROCESSABLE_ENTITY = new StatusType() {
		
		@Override
		public int getStatusCode() {
			return 422;
		}
		
		@Override
		public Response.Status.Family getFamily() {
			return Response.Status.Family.CLIENT_ERROR;
		}
		
		@Override
		public String getReasonPhrase() {
			return "Unprocessable Entity";
		}
	};
	
	private static StatusType DEFAULT_STATUS_CODE = RESTAssert.UNPROCESSABLE_ENTITY;
	
	/** Pattern for date 'yyyy-MM-dd' */
	public static final String PATTERN_DATE = "\\d{4}-\\d{2}-\\d{2}";
	/** Pattern for time 'HH:mm:ss' */
	public static final String PATTERN_TIME = "[0-2][0-9]:[0-5][0-9]:[0-5][0-9]";
	/** Pattern for datetime 'yyyy-MM-dd HH:mm:ss' */
	public static final String PATTERN_DATETIME = RESTAssert.PATTERN_DATE + " " + RESTAssert.PATTERN_TIME;
	/** Pattern for ISO date */
	public static final String PATTERN_ISO = RESTAssert.PATTERN_DATE + "([tT]" + RESTAssert.PATTERN_TIME + "(\\.\\d{1,3})?)?([zZ]|[+-]\\d{2}:?\\d{2})?";
	
	
	/**
	 * @param _status the new default status code
	 */
	public static void changeFailStatus(final Status _status) {
		RESTAssert.DEFAULT_STATUS_CODE = _status;
	}
	
	// #############################################################
	// Basic methods to assert true/false (used by all other assertions)
	// #############################################################
	
	private RESTAssert() {
		//
	}
	
	/**
	 * returns if condition evaluates to true and throws {@link WebApplicationException}(422) if it evaluates to false
	 * 
	 * @param condition the condition to assert
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity) if condition is <i>false</i>
	 */
	public static void assertTrue(final boolean condition) {
		RESTAssert.assertTrue(condition, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * returns if condition evaluates to true and throws {@link WebApplicationException} if it evaluates to false
	 * 
	 * @param condition the condition to assert
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code if condition is <i>false</i>
	 */
	public static void assertTrue(final boolean condition, final StatusType status) {
		RESTAssert.assertFalse(!condition, status);
	}
	
	/**
	 * returns if condition evaluates to false and throws {@link WebApplicationException}(422) if it evaluates to true
	 * 
	 * @param condition the condition to assert
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity) if condition is <i>true</i>
	 */
	public static void assertFalse(final boolean condition) {
		RESTAssert.assertFalse(condition, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * returns if condition evaluates to false and throws {@link WebApplicationException} if it evaluates to true
	 * 
	 * @param condition the condition to assert
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code if condition is <i>true</i>
	 */
	public static void assertFalse(final boolean condition, final StatusType status) {
		if (condition) {
			RESTAssert.fail(status);
		}
	}
	
	/**
	 * fails every time; same as assertTrue(false)
	 * 
	 * @param status the status to fail with
	 * @throws WebApplicationException with given status code
	 */
	public static void fail(final StatusType status) {
		throw new WebApplicationException(Response.status(status).build());
	}
	
	/**
	 * fails every time; same as assertTrue(false)
	 * 
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void fail() {
		RESTAssert.fail(RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	// ###############################################################
	// convenience methods to assert more special values
	// ###############################################################
	
	/**
	 * assert that object is not null
	 * 
	 * @param object the object to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertNotNull(final Object object) {
		RESTAssert.assertNotNull(object, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that object is not null
	 * 
	 * @param object the object to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertNotNull(final Object object, final StatusType status) {
		RESTAssert.assertTrue(object != null, status);
	}
	
	/**
	 * assert that string is not null nor empty
	 * 
	 * @param string the string to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertNotEmpty(final String string) {
		RESTAssert.assertNotEmpty(string, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that string is not null nor empty
	 * 
	 * @param string the string to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertNotEmpty(final String string, final StatusType status) {
		RESTAssert.assertNotNull(string, status);
		RESTAssert.assertFalse(string.isEmpty(), status);
	}
	
	/**
	 * assert that collection is not empty
	 * 
	 * @param collection the collection to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertNotEmpty(final Collection<?> collection) {
		RESTAssert.assertNotEmpty(collection, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that collection is not empty
	 * 
	 * @param collection the collection to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertNotEmpty(final Collection<?> collection, final StatusType status) {
		RESTAssert.assertNotNull(collection, status);
		RESTAssert.assertFalse(collection.isEmpty(), status);
	}
	
	/**
	 * assert that collection has one element
	 * 
	 * @param collection the collection to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertSingleElement(final Collection<?> collection) {
		RESTAssert.assertSingleElement(collection, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that collection has one element
	 * 
	 * @param collection the collection to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertSingleElement(final Collection<?> collection, final StatusType status) {
		RESTAssert.assertNotNull(collection, status);
		RESTAssert.assertTrue(collection.size() == 1, status);
	}
	
	/**
	 * assert that objects are equal.<br>
	 * This means they are both <i>null</i> or <code>one.equals(two)</code> returns <i>true</i>
	 * 
	 * @param one the first object
	 * @param two the second object
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertEquals(final Object one, final Object two) {
		RESTAssert.assertEquals(one, two, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that objects are equal.<br>
	 * This means they are both <i>null</i> or <code>one.equals(two)</code> returns <i>true</i>
	 * 
	 * @param one the first object
	 * @param two the second object
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	@SuppressWarnings("null")
	public static void assertEquals(final Object one, final Object two, final StatusType status) {
		if ((one == null) && (two == null)) {
			return;
		}
		RESTAssert.assertNotNull(one, status);
		RESTAssert.assertTrue(one.equals(two), status);
	}
	
	/**
	 * assert that string matches [+-]?[0-9]*
	 * 
	 * @param string the string to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertInt(final String string) {
		RESTAssert.assertInt(string, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that string matches [+-]?[0-9]*
	 * 
	 * @param string the string to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertInt(final String string, final StatusType status) {
		RESTAssert.assertNotEmpty(string);
		RESTAssert.assertPattern(string, "[+-]?[0-9]*", status);
	}
	
	/**
	 * assert that string matches the given pattern
	 * 
	 * @param string the string to check
	 * @param pattern the pattern to check
	 * @throws WebApplicationException with status code 422 (Unprocessable Entity)
	 */
	public static void assertPattern(String string, String pattern) {
		RESTAssert.assertPattern(string, pattern, RESTAssert.DEFAULT_STATUS_CODE);
	}
	
	/**
	 * assert that string matches the given pattern
	 * 
	 * @param string the string to check
	 * @param status the status code to throw
	 * @throws WebApplicationException with given status code
	 */
	public static void assertPattern(String string, String pattern, final StatusType status) {
		RESTAssert.assertNotNull(string);
		RESTAssert.assertNotNull(pattern);
		RESTAssert.assertTrue(string.matches(pattern), status);
	}
	
}
