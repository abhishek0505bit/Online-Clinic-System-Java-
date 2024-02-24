package com.online_clinic_system.controller;

import java.util.Scanner;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.service.ClinicSystemService;
import com.online_clinic_system.service.ClinicSystemServiceInterface;

public class ClinicSystemController implements ClinicSystemControllerInterface {

	public String SignInClinic() {
		// TODO Auto-generated method stub
		ClinicSystemServiceInterface clinicservice = new ClinicSystemService();
		String returnUserType = null;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter ID");
		String id = s.next();
		System.out.println("Enter password");
		String password = s.next();
		CredentialsBean userCredentialSet = new CredentialsBean();
		userCredentialSet.setUserID(id);
		userCredentialSet.setPassword(password);
		
		returnUserType = clinicservice.ValidateLogin(userCredentialSet);
		
		return returnUserType;	
	}
     
}
