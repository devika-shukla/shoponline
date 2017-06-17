package com.devika.shop.exception;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2952734749620985900L;

	public ApiException(String message) {
		super(message);
	}

}
