package com.example.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.common.messages.ErrorMessages;
import com.example.assignment.common.utility.Utility;
import com.example.assignment.model.CreateNewAssignmentRequest;
import com.example.assignment.model.Response;
import com.example.assignment.service.AssignmentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	Logger logger = LoggerFactory.getLogger(AssignmentController.class);
	Response response;

	@Autowired
	AssignmentService assignmentService;
	
	@RequestMapping(value = "/createNewAssignment", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Create New Assignment", response = Response.class, tags = { "Assignment" })
	public Response createNewAssignment(
			@RequestParam(value = "request", required = true) @ApiParam(value = "example : {\"teacherId \": \"1 \", \"studentId \": \"2 \", \"assignmentName \": \"Assignment1 \", \"assignmentDescription \" : \"CRUD Application \"}", required = true, name = "request") String stringRequest) throws AssignmentException, JsonParseException, JsonMappingException,Exception {
			logger.info("CreateNewAssignment - Request{} " + stringRequest );
			response = new Response();

			CreateNewAssignmentRequest request = new ObjectMapper().readValue(stringRequest,CreateNewAssignmentRequest.class);
			Utility.validateRequest(request);
			
			assignmentService.createNewAssignment(request);
			response.setSuccessResponse();
			return response;
	}
	
	@RequestMapping(value = "/updateAssignment" , method = RequestMethod.PUT , produces = "application/json")
	@ApiOperation(value = "Update Assignment" , response = Response.class , tags = {"Assignment"})
	@ApiResponses(value = { @ApiResponse(code = ErrorMessages.SUCCESS_CODE, message = ErrorMessages.SUCCESS_MESSAGE),
		@ApiResponse(code = ErrorMessages.INTERNAL_SERVER_ERROR_CODE, message = ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE)})
	
	public Response updateAssignment(
			@RequestBody(required = true) @ApiParam(required = true, name = "updateAssignmentRequest") String assignmentName) throws Exception 
	{
		response = new Response();
		String description = " ";
		logger.info("Update Assignment - request{} " + assignmentName);
		assignmentService.updateAssignment(assignmentName,description);
		response.setSuccessResponse();
		logger.info("Update List - response{} " + response.toString());
		return response;
	}
	
	@RequestMapping(value = "/deleteAssignment" , method = RequestMethod.PUT , produces = "application/json")
	@ApiOperation(value = "Delete Assignment" , response = Response.class , tags = {"Assignment"})
	@ApiResponses(value = { @ApiResponse(code = ErrorMessages.SUCCESS_CODE, message = ErrorMessages.SUCCESS_MESSAGE),
		@ApiResponse(code = ErrorMessages.INTERNAL_SERVER_ERROR_CODE, message = ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE)})
	
	public Response deleteAssignment(
			@RequestBody(required = true) @ApiParam(required = true, name = "updateAssignmentRequest") String assignmentName) throws Exception 
	{
		response = new Response();
		logger.info("Delete Assignment - request{} " + assignmentName);

		assignmentService.deleteAssignment(assignmentName);
		
		response.setSuccessResponse();
		logger.info("Delete Assignment List - response{} " + response.toString());
		return response;
	}
	
}
