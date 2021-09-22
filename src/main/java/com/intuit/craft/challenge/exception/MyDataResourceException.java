package com.intuit.craft.challenge.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class MyDataResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyDataResourceException(String message, Throwable t) {
		super(message, t);
	}
}
