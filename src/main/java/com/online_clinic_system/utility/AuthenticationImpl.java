package com.online_clinic_system.utility;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.service.AuthenticationServiceImpl;
import com.online_clinic_system.service.AuthenticationServiceInterface;

public class AuthenticationImpl implements Authentication {

	private AuthenticationServiceInterface authenticationService;

    public AuthenticationImpl() {
        this.authenticationService = new AuthenticationServiceImpl();
    }

    @Override
    public boolean authenticate(CredentialsBean credentialsBean) {
        // Implement authentication logic here (e.g., check user credentials)
    	boolean ans = authenticationService.authenticateUser(credentialsBean);
    	//System.out.println("c"+" "+ans);
        return ans;
    }

    @Override
    public String authorize(String userId) {
        // Implement authorization logic here (e.g., check user role based on userId)
        // For simplicity, assume roles as "A" (Admin), "R" (Reporter), and "P" (Patient)
        // You can fetch the user's role from the database or any other data source
        if (userId.equals("A")) {
            return "A"; // Admin
        } else if (userId.equals("R")) {
            return "R"; // Reporter
        } else if (userId.equals("P")) {
            return "P"; // Patient
        } else {
            return "INVALID"; // Invalid user
        }
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) {
        // Implement logic to change login status
        return authenticationService.changeLoginStatus(credentialsBean, loginStatus);
    }

    @Override
    public String login(CredentialsBean credentialsBean) {
        // Authenticate the user
        if (authenticate(credentialsBean)) {
            // If authentication is successful, return the user's role
            String userType = credentialsBean.getUserType();
           System.out.println(userType);
            return authorize(userType);
        } else {
            // If authentication fails, return "INVALID"
            return "INVALID";
        }
    }

    @Override
    public boolean logout(String userId) {
        // Implement logic to log out the user and change login status
        return authenticationService.logoutUser(userId);
    }

    @Override
    public String changePassword(CredentialsBean credentialsBean, String newPassword) {
        // Implement logic to change the user's password
        return authenticationService.changePassword(credentialsBean, newPassword);
    }


}
