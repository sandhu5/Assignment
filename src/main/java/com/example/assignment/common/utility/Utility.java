package com.example.assignment.common.utility;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.common.messages.ErrorMessages;

public class Utility {

	public static void validateRequest(Object object) throws AssignmentException {
		Validator validator = prepareValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(object);
		if (!violations.isEmpty()) {
			ConstraintViolation<Object> constraintViolations = violations.iterator().next();
			throw new AssignmentException(ErrorMessages.INVALID_REQUEST_CODE, constraintViolations.getMessage());
		}
	}

	private static Validator prepareValidator() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator;
	}
}