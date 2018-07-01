package com.example.assignment.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserRequest {

	@NotBlank(message = "Please provide userType")
	@NotEmpty(message = "userType cannot be empty")
	String userType;
	
	@NotBlank(message = "Please provide userName")
	@NotEmpty(message = "userName cannot be empty")
	String userName;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
