package com.online_clinic_system.controller;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.PatientBean;

public interface ClinicPatientControllerInterface {

	void addAilmentDetailsView(String userId);
	
	void modifyAilmentDetailsView();
	
	void viewAilmentDetailsView(String userId);

	boolean insertUserCredentials(CredentialsBean newUserCredentials);

	boolean insertPatientDetails(PatientBean patientDetails);

	void viewListOfDoctorView(String userID);

	void requestAppointmentView(String userID);

	

	

}
