package com.example.assignment.dao;

import com.example.assignment.model.CreateNewAssignmentRequest;

public interface AssignmentDao {

	int insertNewAssignment(CreateNewAssignmentRequest createNewAssignmentRequest) ;
	
	String fetchStudentName(int studentId);

	String fetchTeacherName(int studentId);

	int fetchAssignmentId(String assignmentName);

	void updateAssignment(int assignmentId, String descreiption);

	int fetchIfValidToDelete(String assignmentName);

	void deleteAssignment(int assignmentId);

}
