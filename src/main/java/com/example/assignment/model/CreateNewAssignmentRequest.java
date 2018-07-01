package com.example.assignment.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateNewAssignmentRequest {

	private String teacherName;
	private String studentName;
	
	@NotBlank(message = "Please provide assignmentName")
	@NotEmpty(message = "assignmentName cannot be empty")
	private String assignmentName;

	@NotNull(message="teacherId can't be zero")
	@Min(value=1, message="teacherId can't be zero")
	private int teacherId;

	@NotBlank(message = "Please provide assignment Description")
	@NotEmpty(message = "Assignment Description cannot be empty")
	private String assignmentDescription;

	@NotNull(message="teacherId can't be zero")
	@Min(value=1, message="teacherId can't be zero")
	private int studentId;

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getAssignmentDescription() {
		return assignmentDescription;
	}

	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
}