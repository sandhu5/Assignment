package com.example.assignment.service.implementation.common;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.common.messages.ErrorMessages;
import com.example.assignment.common.model.UserRequest;
import com.example.assignment.dao.implementation.common.UserDaoImplementation;

@Service
public class UserServiceImplementation {
	Logger logger = Logger.getLogger(UserServiceImplementation.class.getName());

	@Autowired
	UserDaoImplementation userDaoImplementation;

	public void userRegistration(UserRequest userRequest) throws AssignmentException {
		try {
			String userType = userRequest.getUserType();
			userDaoImplementation.insertUserMaster(userRequest, userType);
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new AssignmentException(ErrorMessages.INTERNAL_SERVER_ERROR_CODE,	ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}
