package com.intuit.craft.challenge.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
