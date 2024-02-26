package com.webapp.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webapp.project.models.User;

public class UserDAO {
	private Connection connection;

	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	public User authenticateUser(String username, String password) throws SQLException {
	    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, username);
	        statement.setString(2, password);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int userId = resultSet.getInt("user_id");
	                String email = resultSet.getString("email");
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");
	                String address = resultSet.getString("address");
	                boolean isAdmin = resultSet.getBoolean("admin");
	                
	                return new User(userId, username, password, email, firstName, lastName, address, isAdmin);
	            } else {
	                return null;
	            }
	        }
	    }
	}


	
    
}
