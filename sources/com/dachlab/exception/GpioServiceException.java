package com.dachlab.exception;

public class GpioServiceException extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for GPIO services exceptions.
	 * 
	 * @param message
	 *            messages of the exception.
	 * @param cause
	 *            cause of the exception
	 */
	public GpioServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for GPIO services exceptions.
	 * 
	 * @param message
	 *            messages of the exception.
	 */
	public GpioServiceException(String message) {
		super(message);
	}
}
