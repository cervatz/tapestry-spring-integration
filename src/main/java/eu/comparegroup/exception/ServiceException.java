package eu.comparegroup.exception;

import java.io.Serializable;

public class ServiceException extends Exception implements Serializable {

	/**
	 * Empty constructor.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Constructor with message.
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and throwable.
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with throwable.
	 */	
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 */
	protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
