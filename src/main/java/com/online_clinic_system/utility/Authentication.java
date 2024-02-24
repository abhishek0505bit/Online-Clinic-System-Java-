package com.online_clinic_system.utility;

import com.online_clinic_system.entity.CredentialsBean;

public interface Authentication {
	public boolean authenticate(CredentialsBean credentialsBean);

	public String authorize(String userId);

	public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus);

	 public String login(CredentialsBean credentialsBean);

	 public boolean logout(String userId);

	 public String changePassword(CredentialsBean credentialsBean, String newPassword);
}
