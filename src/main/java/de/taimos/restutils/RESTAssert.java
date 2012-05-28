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
public class RESTAssert {

	// #############################################################
	// Basic methods to assert true/false (used by all other assertions)
	// #############################################################

	/**
	 * returns if condition evaluates to true and throws {@link WebApplicationException}(412) if it evaluates to false
	 * 
	 * @param condition
	 *            the condition to assert
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed) if condition is <i>false</i>
	 */
	public static void assertTrue(boolean condition) {
		RESTAssert.assertTrue(condition, Status.PRECONDITION_FAILED);
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
	public static void assertTrue(boolean condition, Status status) {
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
	public static void assertFalse(boolean condition) {
		RESTAssert.assertFalse(condition, Status.PRECONDITION_FAILED);
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
	public static void assertFalse(boolean condition, Status status) {
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
	public static void assertNotNull(Object object) {
		RESTAssert.assertNotNull(object, Status.PRECONDITION_FAILED);
	}

	/**
	 * assert that object is not null
	 * 
	 * @param object
	 *            the object to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotNull(Object object, Status status) {
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
	public static void assertNotEmpty(String string) {
		RESTAssert.assertNotEmpty(string, Status.PRECONDITION_FAILED);
	}

	/**
	 * assert that string is not null nor empty
	 * 
	 * @param string
	 *            the string to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotEmpty(String string, Status status) {
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
	public static void assertNotEmpty(Collection<?> collection) {
		RESTAssert.assertNotEmpty(collection, Status.PRECONDITION_FAILED);
	}

	/**
	 * assert that collection is not empty
	 * 
	 * @param collection
	 *            the collection to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertNotEmpty(Collection<?> collection, Status status) {
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
	public static void assertSingleElement(Collection<?> collection) {
		RESTAssert.assertSingleElement(collection, Status.PRECONDITION_FAILED);
	}

	/**
	 * assert that collection has one element
	 * 
	 * @param collection
	 *            the collection to check
	 * @param status
	 *            the status code to throw
	 * @throws WebApplicationException
	 *             with status code 412 (Precondition failed)
	 */
	public static void assertSingleElement(Collection<?> collection, Status status) {
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
	public static void assertEquals(Object one, Object two) {
		RESTAssert.assertEquals(one, two, Status.PRECONDITION_FAILED);
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
	 *             with status code 412 (Precondition failed)
	 */
	@SuppressWarnings("null")
	public static void assertEquals(Object one, Object two, Status status) {
		if ((one == null) && (two == null)) {
			return;
		}
		RESTAssert.assertNotNull(one, status);
		RESTAssert.assertTrue(one.equals(two), status);
	}

}
