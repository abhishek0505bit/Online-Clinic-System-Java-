package com.online_clinic_system.service;

import com.online_clinic_system.entity.CredentialsBean;

public interface AuthenticationServiceInterface {

	boolean authenticateUser(CredentialsBean credentialsBean);

	boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus);

	boolean logoutUser(String userId);

	String changePassword(CredentialsBean credentialsBean, String newPassword);

	

}
