package com.example.assignment.common.exception;

public class AssignmentException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private Integer errorCode;

	public AssignmentException() {
		super();
	}

	public AssignmentException(String message, Exception exp) {
		super(message, exp);
	}

	public AssignmentException(String ErrorMessage) {
		this.errorMessage = ErrorMessage;
	}

	public AssignmentException(int errorCode) {
		this.errorCode = errorCode;
	}

	public AssignmentException(Integer ErrorCode, String ErrorMessage) {
		this.errorMessage = ErrorMessage;
		this.errorCode = ErrorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "[errorCode=" + errorCode + ",errorMessage=" + errorMessage + "]";
	}

}
