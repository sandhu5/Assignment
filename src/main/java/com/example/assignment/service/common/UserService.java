package com.example.assignment.service.common;

import java.io.InputStream;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.model.CreateNewAssignmentRequest;

public interface UserService {

	public void createNewAssignment(CreateNewAssignmentRequest request, InputStream inputStream) throws AssignmentException;
}
