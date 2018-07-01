package com.example.assignment.common.model;

import com.example.assignment.common.messages.ErrorMessages;

import io.swagger.annotations.ApiModelProperty;

public class CommonResponse {
	private int responseCode;
	private String responseMessage;

	public CommonResponse() {
		super();
	}
	public CommonResponse(int responseCode, String responseMessage) {
		this();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	@ApiModelProperty(value = "Response Code", example = "1000", required = true, position = 1)
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@ApiModelProperty(value = "Response Message", example = "Success", required = true, position = 2)
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setSuccessResponse() {
		responseCode = ErrorMessages.SUCCESS_CODE;
		responseMessage = ErrorMessages.SUCCESS_MESSAGE;
	}
	public void setErrorResponse(int errorCode, String errorMessage) {
		this.responseCode = errorCode;
		this.responseMessage = errorMessage;
	}
}
