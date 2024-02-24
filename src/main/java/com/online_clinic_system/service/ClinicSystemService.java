package com.online_clinic_system.service;

import com.online_clinic_system.dao.ClinicSystemDAO;
import com.online_clinic_system.dao.ClinicSystemDAOInterface;
import com.online_clinic_system.entity.CredentialsBean;

public class ClinicSystemService implements ClinicSystemServiceInterface {

	@Override
	public String ValidateLogin(CredentialsBean userCredentialSet) {
		// TODO Auto-generated method stub
		String returnservicestring = null;
		ClinicSystemDAOInterface clinicDao= new ClinicSystemDAO();
		returnservicestring = clinicDao.validateLogin(userCredentialSet);
		return returnservicestring;
		
	}

}
