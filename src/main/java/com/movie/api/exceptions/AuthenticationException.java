package com.movie.api.exceptions;

public class AuthenticationException extends RuntimeException {
	
	private static final long serialVersionUID = 10790560669686121L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
