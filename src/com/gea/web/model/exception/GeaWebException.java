package com.gea.web.model.exception;

public class GeaWebException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GeaWebException(String message) {
		super(message);
	}

	public GeaWebException() {
		super();
	}

	public GeaWebException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeaWebException(Throwable cause) {
		super(cause);
	}
}
