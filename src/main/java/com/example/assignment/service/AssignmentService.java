package com.example.assignment.service;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.model.CreateNewAssignmentRequest;

public interface AssignmentService {
	public void createNewAssignment(CreateNewAssignmentRequest request) throws AssignmentException;

	public void updateAssignment(String assignmentName, String discription) throws AssignmentException;

	public void deleteAssignment(String assignmentName) throws AssignmentException;
	
}
