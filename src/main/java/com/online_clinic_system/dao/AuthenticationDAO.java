package com.online_clinic_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.utility.DatabaseConnection;

public class AuthenticationDAO implements AuthenticationDAOInterface {
	// Database connection
    private Connection connection;

    public AuthenticationDAO() {
        // Initialize the database connection (you can use your preferred method)
        try {
			connection = DatabaseConnection.getConnection();
		} catch (SQLException | ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @Override
    public boolean authenticateUser(CredentialsBean credentialsBean) {
        // Implement authentication logic to check user credentials in the database
        try {
            String query = "SELECT * FROM OCS_TBL_User_Credentials WHERE USERID = ? AND PASSWORD = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credentialsBean.getUserID());
            preparedStatement.setString(2, credentialsBean.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	credentialsBean.setUserType(resultSet.getString("USERTYPE"));
            	return true;
            	}
            
            return false; // User found if there is at least one row in the result set
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Authentication failed
        }
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) {
        // Implement logic to change the login status in the database
        try {
            String query = "UPDATE OCS_TBL_User_Credentials SET LOGINSTATUS = ? WHERE USERID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loginStatus);
            preparedStatement.setString(2, credentialsBean.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Login status updated if rowsAffected > 0
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Login status update failed
        }
    }

    @Override
    public String changePassword(CredentialsBean credentialsBean, String newPassword) {
        // Implement logic to change the user's password in the database
        try {
            String query = "UPDATE OCS_TBL_User_Credentials SET PASSWORD = ? WHERE USERID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, credentialsBean.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0 ? "SUCCESS" : "FAIL"; // Password updated if rowsAffected > 0
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL"; // Password update failed
        }
    }

    @Override
    public boolean logoutUser(String userId) {
        // Implement logic to log out a user by changing their login status to 0
        try {
            String query = "UPDATE OCS_TBL_User_Credentials SET LOGINSTATUS = 0 WHERE USERID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Logout successful if rowsAffected > 0
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Logout failed
        }
    }
}
