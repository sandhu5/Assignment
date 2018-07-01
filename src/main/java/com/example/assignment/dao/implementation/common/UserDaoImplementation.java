package com.example.assignment.dao.implementation.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.example.assignment.common.model.UserRequest;

@Repository
public class UserDaoImplementation {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertUserMaster(UserRequest userRequest, String userType) {
		
		String query = "INSERT INTO "+userType.toLowerCase()+"_master ("+userType+"_name) VALUES(?)";

		final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, userRequest.getUserName());
				return preparedStatement;
			}
		};
		jdbcTemplate.update(preparedStatementCreator);
	}
}