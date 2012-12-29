package de.taimos.restutils;

import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

/**
 * Use this class to assert preconditions in REST Handler<br>
 * <br>
 * assert methods throw {@link WebApplicationException} on failure
 * 
 * @author thoeger
 * 
 */
public final class RESTAssert {

	/**
	 * The default status code that is thrown<br>
	 * HTTP 412 (Precondition failed)
	 */
	private static final Status DEFAULT_STATUS_CODE = Status.PRECONDITION_FAILED;

	// #############################################################
	// Basic methods to assert true/false (used by all other assertions)
	// #############################################################

	private RESTAssert() {
		//
	}

	/**
	 * returns if condition evaluates to true and throws {@link WebApplicationException}(412) if it evaluates to false
	 * 
	 * @param condition
	 *            the condition to assert
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed) if condition is <i>false</i>
	 */
	public static void assertTrue(final boolean condition) {
		RESTAssert.assertTrue(condition, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * returns if condition evaluates to true and throws {@link WebApplicationException} if it evaluates to false
	 * 
	 * @param condition
	 *            the condition to assert
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code if condition is <i>false</i>
	 */
	public static void assertTrue(final boolean condition, final Status status) {
		RESTAssert.assertFalse(!condition, status);
	}

	/**
	 * returns if condition evaluates to false and throws {@link WebApplicationException}(412) if it evaluates to true
	 * 
	 * @param condition
	 *            the condition to assert
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed) if condition is <i>true</i>
	 */
	public static void assertFalse(final boolean condition) {
		RESTAssert.assertFalse(condition, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * returns if condition evaluates to false and throws {@link WebApplicationException} if it evaluates to true
	 * 
	 * @param condition
	 *            the condition to assert
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code if condition is <i>true</i>
	 */
	public static void assertFalse(final boolean condition, final Status status) {
		if (condition) {
			throw new WebApplicationException(status);
		}
	}

	// ###############################################################
	// convenience methods to assert more special values
	// ###############################################################

	/**
	 * assert that object is not null
	 * 
	 * @param object
	 *            the object to check
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotNull(final Object object) {
		RESTAssert.assertNotNull(object, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * assert that object is not null
	 * 
	 * @param object
	 *            the object to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code
	 */
	public static void assertNotNull(final Object object, final Status status) {
		RESTAssert.assertTrue(object != null, status);
	}

	/**
	 * assert that string is not null nor empty
	 * 
	 * @param string
	 *            the string to check
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotEmpty(final String string) {
		RESTAssert.assertNotEmpty(string, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * assert that string is not null nor empty
	 * 
	 * @param string
	 *            the string to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code
	 */
	public static void assertNotEmpty(final String string, final Status status) {
		RESTAssert.assertNotNull(string, status);
		RESTAssert.assertFalse(string.isEmpty(), status);
	}

	/**
	 * assert that collection is not empty
	 * 
	 * @param collection
	 *            the collection to check
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotEmpty(final Collection<?> collection) {
		RESTAssert.assertNotEmpty(collection, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * assert that collection is not empty
	 * 
	 * @param collection
	 *            the collection to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code
	 */
	public static void assertNotEmpty(final Collection<?> collection, final Status status) {
		RESTAssert.assertNotNull(collection, status);
		RESTAssert.assertFalse(collection.isEmpty(), status);
	}

	/**
	 * assert that collection has one element
	 * 
	 * @param collection
	 *            the collection to check
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertSingleElement(final Collection<?> collection) {
		RESTAssert.assertSingleElement(collection, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * assert that collection has one element
	 * 
	 * @param collection
	 *            the collection to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code
	 */
	public static void assertSingleElement(final Collection<?> collection, final Status status) {
		RESTAssert.assertNotNull(collection, status);
		RESTAssert.assertTrue(collection.size() == 1, status);
	}

	/**
	 * assert that objects are equal.<br>
	 * This means they are both <i>null</i> or <code>one.equals(two)</code> returns <i>true</i>
	 * 
	 * @param one
	 *            the first object
	 * @param two
	 *            the second object
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertEquals(final Object one, final Object two) {
		RESTAssert.assertEquals(one, two, RESTAssert.DEFAULT_STATUS_CODE);
	}

	/**
	 * assert that objects are equal.<br>
	 * This means they are both <i>null</i> or <code>one.equals(two)</code> returns <i>true</i>
	 * 
	 * @param one
	 *            the first object
	 * @param two
	 *            the second object
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with given status code
	 */
	@SuppressWarnings("null")
	public static void assertEquals(final Object one, final Object two, final Status status) {
		if ((one == null) && (two == null)) {
			return;
		}
		RESTAssert.assertNotNull(one, status);
		RESTAssert.assertTrue(one.equals(two), status);
	}

}
