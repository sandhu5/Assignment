package com.example.assignment.controller.common;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.common.messages.ErrorMessages;
import com.example.assignment.common.model.UserRequest;
import com.example.assignment.common.utility.Utility;
import com.example.assignment.model.Response;
import com.example.assignment.service.implementation.common.UserServiceImplementation;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/register")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	UserServiceImplementation userServiceImplementation;

	Response response;
	
	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "User Registration", response = Response.class, tags = { "User" })
	@ApiResponses(value = { @ApiResponse(code = ErrorMessages.SUCCESS_CODE, message = ErrorMessages.SUCCESS_MESSAGE),
			@ApiResponse(code = ErrorMessages.INVALID_REQUEST_CODE, message = ErrorMessages.INVALID_REQUEST_MESSAGE),
			@ApiResponse(code = ErrorMessages.INTERNAL_SERVER_ERROR_CODE, message = ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE) })
	public Response userRegistration(
			@RequestBody(required = true)  UserRequest request)
			throws AssignmentException, Exception {
		logger.info("User Resgistration - request{} " + new Gson().toJson(request));
		response = new Response();
		Utility.validateRequest(request);
		userServiceImplementation.userRegistration(request);
		response.setSuccessResponse();
		logger.info("User Resgistration - response{} " + new Gson().toJson(response));
		return response;
	}
}