/**
 * 
 */
package de.taimos.restutils;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Copyright 2015 Taimos GmbH<br>
 * <br>
 *
 * @author thoeger
 * 		
 */
public class UnprocessableEntityException extends ClientErrorException {
	
	private static final long serialVersionUID = -2740045367479165061L;
	private static final int STATUS_CODE = 422;
	
	
	public UnprocessableEntityException() {
		super(UnprocessableEntityException.STATUS_CODE);
	}
	
	public UnprocessableEntityException(String message) {
		super(message, UnprocessableEntityException.STATUS_CODE);
	}
	
	public UnprocessableEntityException(Response response) {
		super(response);
	}
	
	public UnprocessableEntityException(String message, Response response) {
		super(message, response);
	}
	
	public UnprocessableEntityException(Throwable cause) {
		super(UnprocessableEntityException.STATUS_CODE, cause);
	}
	
	public UnprocessableEntityException(String message, Throwable cause) {
		super(message, UnprocessableEntityException.STATUS_CODE, cause);
	}
	
	public UnprocessableEntityException(Response response, Throwable cause) {
		super(response, cause);
	}
	
	public UnprocessableEntityException(String message, Response response, Throwable cause) {
		super(message, response, cause);
	}
	
}
