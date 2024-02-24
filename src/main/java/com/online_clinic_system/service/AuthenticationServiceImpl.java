package com.online_clinic_system.service;

import com.online_clinic_system.dao.AuthenticationDAO;
import com.online_clinic_system.dao.AuthenticationDAOInterface;
import com.online_clinic_system.entity.CredentialsBean;

public class AuthenticationServiceImpl implements AuthenticationServiceInterface {

	@Override
	public boolean authenticateUser(CredentialsBean credentialsBean) {
		AuthenticationDAOInterface authenticationDAO = new AuthenticationDAO();
		boolean ans =  authenticationDAO.authenticateUser(credentialsBean); 
		//System.out.println("ser" +" "+ ans);
		return ans;
	}

	@Override
	public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) {
		// TODO Auto-generated method stub
		AuthenticationDAOInterface authenticationDAO = new AuthenticationDAO();

		 return authenticationDAO.changeLoginStatus(credentialsBean, loginStatus);
	}

	@Override
	public boolean logoutUser(String userId) {
		// TODO Auto-generated method stub
		AuthenticationDAOInterface authenticationDAO = new AuthenticationDAO();

		return authenticationDAO.logoutUser(userId);
	}

	@Override
	public String changePassword(CredentialsBean credentialsBean, String newPassword) {
		// TODO Auto-generated method stub
		AuthenticationDAOInterface authenticationDAO = new AuthenticationDAO();

		return authenticationDAO.changePassword(credentialsBean, newPassword);
	}

}
