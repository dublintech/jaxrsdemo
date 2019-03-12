package com.donabate.staveley.alex.service.validation;

/**
 * This classs represents exceptions that happen deeper in the stack. 
 * Not simple validation of the payload.
 * 
 * @author astaveley
 */
public class BusinessLogicException extends RuntimeException {
	public BusinessLogicException(String message) {
		super(message);
	}
}
