package com.example.assignment.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.assignment.common.utility.Util;
import com.example.assignment.dao.AssignmentDao;
import com.example.assignment.model.CreateNewAssignmentRequest;

@Repository
public class AssignmentDaoImplementation implements AssignmentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String fetchStudentName(int studentId) {
		String query = "select student_name from student_master where student_id = ? ";
		String studentName = jdbcTemplate.queryForObject(query, new Object[]{studentId}, String.class);
		return studentName;
	}
	
	@Override
	public String fetchTeacherName(int teacherId) {
		String query = "select teacher_name from teacher_master where student_id = ? ";
		String teacherName = jdbcTemplate.queryForObject(query, new Object[]{teacherId}, String.class);
		return teacherName;
	}
	
	@Override
	public int insertNewAssignment(CreateNewAssignmentRequest createNewAssignmentRequest) {
		int assignmentId = 0;
		String query = "INSERT into assignment_master(assignment_name,teacher_name,teacher_id,student_name,student_id, created_date,description,status)VALUES(?,?,?,?,?,?,?,?)";

		final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, createNewAssignmentRequest.getAssignmentName());
				preparedStatement.setString(2, createNewAssignmentRequest.getTeacherName());
				preparedStatement.setInt(3, createNewAssignmentRequest.getTeacherId());
				preparedStatement.setString(4, createNewAssignmentRequest.getStudentName());
				preparedStatement.setInt(5, createNewAssignmentRequest.getStudentId());
				preparedStatement.setTimestamp(6, Util.getCurrentTimeStamp());
				preparedStatement.setString(7, "ACTIVE");
				return preparedStatement;
			}
		};
		final KeyHolder holder = new GeneratedKeyHolder(); // to retrieve keys
		jdbcTemplate.update(preparedStatementCreator, holder);
		assignmentId = holder.getKey().intValue();
		return assignmentId;
	}
	//fetchIfValidToDelete
	@Override
	public int fetchAssignmentId(String assignmentName) {
		String query = "select assignment_id from teacher_master where assignment_name = ? ";
		int assignmentId = jdbcTemplate.queryForObject(query, new Object[]{assignmentName}, Integer.class);
		return assignmentId;
	}

	@Override
	public void updateAssignment(int id ,String descreiption) {
		String query = "update assignment_master set description = ?  where assignment_id = ? ";
		jdbcTemplate.update(query, new Object[]{descreiption,id});
	}

	@Override
	public int fetchIfValidToDelete(String assignmentName) {
		String query = "select assignment_id from teacher_master where assignment_name = ? and MONTH(created_date ) = MONTH( curdate() ) -1;";
		int assignmentId = jdbcTemplate.queryForObject(query, new Object[]{assignmentName}, Integer.class);
		return assignmentId;
	}

	@Override
	public void deleteAssignment(int assignmentId) {
		String query = "delete from assignment_master  where assignment_id = ? ";
		jdbcTemplate.update(query, new Object[]{assignmentId});
	}
}
