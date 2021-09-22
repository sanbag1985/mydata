package com.intuit.craft.challenge.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentNotFoundException(String message) {
		super(message);
	}
}
