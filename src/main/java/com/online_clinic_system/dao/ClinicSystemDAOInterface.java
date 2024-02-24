package com.online_clinic_system.dao;

import com.online_clinic_system.entity.CredentialsBean;

public interface ClinicSystemDAOInterface {

	String validateLogin(CredentialsBean userCredentialSet);

}
