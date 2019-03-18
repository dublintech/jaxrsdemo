package com.donabate.staveley.alex.service.validation;

/**
 * This classs represents exceptions that happen deeper in the stack. 
 * Not simple validation of the payload.
 * 
 * @author astaveley
 */
public class BusinessLogicException extends RuntimeException {
	
	public static enum BusinessErrorCodeEnum {
		ENTIT_NOT_IN_DB, INTERNAL_PROCESSING_ERROR
	}
	
	private BusinessErrorCodeEnum businessErrorCodeEnum = null;
	
	public BusinessLogicException(String message, BusinessErrorCodeEnum businessErrorCodeEnum) {
		super(message);
		this.businessErrorCodeEnum = businessErrorCodeEnum;
	}
}
