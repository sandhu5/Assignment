package com.example.assignment.common.exception;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.assignment.common.messages.ErrorMessages;
import com.example.assignment.common.model.CommonResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.JsonSyntaxException;

@ControllerAdvice
public class CommonExceptionHandler  {
    
	private static Logger logger = Logger.getLogger(CommonExceptionHandler.class.getName());
	
	@ExceptionHandler(AssignmentException.class)
	public @ResponseBody CommonResponse handlePPLException(HttpServletRequest request, AssignmentException ex){
		logger.info("Exception Occured:: URL="+request.getRequestURL()+" ErrorMessage="+ex.getErrorMessage());
		ex.printStackTrace();
		return new CommonResponse(ex.getErrorCode(),ex.getErrorMessage());
	}
	@ExceptionHandler({HttpMessageNotReadableException.class,JsonParseException.class,JsonSyntaxException.class,InvalidFormatException.class})
	public  @ResponseBody CommonResponse handleBadRequestException(HttpServletRequest request, Exception ex){
		logger.info("Exception Occured:: URL="+request.getRequestURL()+" ErrorMessage="+ex.getMessage());
		ex.printStackTrace();
		return new CommonResponse(ErrorMessages.INVALID_REQUEST_CODE, ErrorMessages.INVALID_REQUEST_MESSAGE);		
	}
	@ExceptionHandler(Exception.class)
	public @ResponseBody CommonResponse handleException(HttpServletRequest request, Exception ex){
		logger.info("Exception Occured:: URL="+request.getRequestURL()+" ErrorMessage="+ex.getMessage());
		ex.printStackTrace();
		return new CommonResponse(ErrorMessages.INTERNAL_SERVER_ERROR_CODE, ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE);
	}	
}