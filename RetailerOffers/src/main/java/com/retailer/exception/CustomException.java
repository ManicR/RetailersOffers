package com.retailer.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private String errorCode;

	public CustomException(String code, String message) {
		super(message);
		this.errorCode = code;
	}

	public CustomException(String message) {
		super(message);

	}

}
