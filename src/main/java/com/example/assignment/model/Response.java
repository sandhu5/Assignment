package com.example.assignment.model;

import java.util.Map;

import com.example.assignment.common.messages.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class Response{
	
	private int responseCode;
	private String responseMessage;
	private Map<String, Object> response;
	
	@ApiModelProperty(required = true,value = "Response Code", example = "1000", position = 1)
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	@ApiModelProperty(required = true,value = "Response Message", example = "Success", position = 2)
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	//@ApiModelProperty(value = "Verify Response", example = "{\"responseCode\":1000,\"responseMessage\":\"Success\",\"response\":{\"packs\":\"\"}}", position = 3)
	@ApiModelProperty(position = 3)
	public Map<String, Object> getResponse() {
		return response;
	}
	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
	
	public void setSuccessResponse() {
		this.responseCode = ErrorMessages.SUCCESS_CODE;
		this.responseMessage = ErrorMessages.SUCCESS_MESSAGE;
	}

	public void setErrorResponse(int errorCode, String errorMessage) {
		this.responseCode = errorCode;
		this.responseMessage = errorMessage;
		this.response = null;
	}
	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", response="
				+ response + "]";
	}

}