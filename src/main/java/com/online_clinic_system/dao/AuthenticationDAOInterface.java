package com.online_clinic_system.dao;

import com.online_clinic_system.entity.CredentialsBean;

public interface AuthenticationDAOInterface {

	boolean authenticateUser(CredentialsBean credentialsBean);

	boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus);

	boolean logoutUser(String userId);

	String changePassword(CredentialsBean credentialsBean, String newPassword);


}
