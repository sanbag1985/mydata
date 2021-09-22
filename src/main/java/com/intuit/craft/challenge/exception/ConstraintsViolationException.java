package com.intuit.craft.challenge.exception;

public class ConstraintsViolationException {

	private String field;
	private String message;

	public ConstraintsViolationException(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ConstraintsViolationException(Builder builder) {
		this.field = builder.field;
		this.message = builder.message;
	}

	public static class Builder {
		private String field;
		private String message;

		public Builder setField(String field) {
			this.field = field;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public ConstraintsViolationException build() {
			return new ConstraintsViolationException(this);
		}

	}
}
