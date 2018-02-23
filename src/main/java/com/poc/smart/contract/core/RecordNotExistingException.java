package com.poc.smart.contract.core;

public class RecordNotExistingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3828762096145419115L;

	public RecordNotExistingException() {
		super();
	}

	public RecordNotExistingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public RecordNotExistingException(String message, Throwable cause) {
		super(message, cause);

	}

	public RecordNotExistingException(String message) {
		super(message);

	}

	public RecordNotExistingException(Throwable cause) {
		super(cause);

	}

}
