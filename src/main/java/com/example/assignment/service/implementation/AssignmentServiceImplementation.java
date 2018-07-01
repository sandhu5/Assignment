package com.example.assignment.service.implementation;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.assignment.common.exception.AssignmentException;
import com.example.assignment.common.messages.ErrorMessages;
import com.example.assignment.dao.AssignmentDao;
import com.example.assignment.model.CreateNewAssignmentRequest;
import com.example.assignment.service.AssignmentService;

@Service
public class AssignmentServiceImplementation implements AssignmentService {

	@Autowired
	private PlatformTransactionManager transactionManager;

	Logger logger = Logger.getLogger(AssignmentServiceImplementation.class.getName());

	@Autowired
	AssignmentDao assignmentDaoImplementation;

	@Override
	public void createNewAssignment(CreateNewAssignmentRequest createNewAssignmentRequest ) throws AssignmentException {
		TransactionStatus transactionStatus = null;
		TransactionDefinition transactionDefinition = null;
		try {
			transactionDefinition = new DefaultTransactionDefinition();
			transactionStatus = transactionManager.getTransaction(transactionDefinition);
			String teacherName = checkTeacher(createNewAssignmentRequest.getTeacherId());
			String studentName = checkStudent(createNewAssignmentRequest.getStudentId());
			createNewAssignmentRequest.setTeacherName(teacherName);
			createNewAssignmentRequest.setStudentName(studentName);
			insertNewAssignmentDetails(createNewAssignmentRequest);
			transactionManager.commit(transactionStatus);
		} catch (AssignmentException ex) {
			if (transactionStatus != null) {
				transactionManager.rollback(transactionStatus);
			}
			throw ex;
		} catch (Exception ex) {
			if (transactionStatus != null) {
				transactionManager.rollback(transactionStatus);
			}
			ex.printStackTrace();
			throw new AssignmentException(ErrorMessages.INTERNAL_SERVER_ERROR_CODE, ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
	
	private String checkStudent(int studentId) throws AssignmentException {
		String studentName = assignmentDaoImplementation.fetchStudentName(studentId);
		if (studentName==null || studentName.trim().isEmpty()) {
			throw new AssignmentException(ErrorMessages.INVALID_STUDENT_CODE, ErrorMessages.INVALID_STUDENT_MESSAGE);
		}
		return studentName;
	}

	private String checkTeacher(int teacherId) throws AssignmentException {
		String teacherName = assignmentDaoImplementation.fetchStudentName(teacherId);
		if (teacherName==null || teacherName.trim().isEmpty()) {
			throw new AssignmentException(ErrorMessages.INVALID_TEACHER_CODE, ErrorMessages.INVALID_TEACHER_MESSAGE);
		}
		return teacherName;
	}

	private void insertNewAssignmentDetails(CreateNewAssignmentRequest createNewAssignmentRequest) throws AssignmentException {
		int assignmentId = assignmentDaoImplementation.insertNewAssignment(createNewAssignmentRequest);
		if (assignmentId <= 0) {
			throw new AssignmentException(ErrorMessages.INTERNAL_SERVER_ERROR_CODE, ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}

	@Override
	public void updateAssignment(String assignmentName,String descreiption) throws AssignmentException {
		int assignmentId = checkAssignment(assignmentName);
		assignmentDaoImplementation.updateAssignment(assignmentId,descreiption);
	}

	private int checkAssignment(String assignmentName) throws AssignmentException {
		int assignmentId = assignmentDaoImplementation.fetchAssignmentId(assignmentName);
		if (assignmentId == 0) {
			throw new AssignmentException(ErrorMessages.INVALID_ASSIGNMENT_CODE, ErrorMessages.INVALID_ASSIGNMENT_MESSAGE);
		}
		return assignmentId;
	}

	@Override
	public void deleteAssignment(String assignmentName) throws AssignmentException {
		int assignmentId = isAssignmentValidToDelete(assignmentName);
		assignmentDaoImplementation.deleteAssignment(assignmentId);
	}
	
	
	private int isAssignmentValidToDelete(String assignmentName) throws AssignmentException {
		int assignmentId = assignmentDaoImplementation.fetchIfValidToDelete(assignmentName);
		if (assignmentId == 0) {
			throw new AssignmentException(ErrorMessages.INVALID_ASSIGNMENT_CODE, ErrorMessages.INVALID_ASSIGNMENT_MESSAGE);
		}
		return assignmentId;
	}
}
