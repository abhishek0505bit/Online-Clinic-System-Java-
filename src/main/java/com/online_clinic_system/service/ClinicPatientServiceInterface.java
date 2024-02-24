package com.online_clinic_system.service;

import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public interface ClinicPatientServiceInterface {

	boolean insertUserCredentialS(CredentialsBean newUserCredentials);

	boolean insertPatientDetailS(PatientBean patientDetails);

	String addAilmentServiceDetails(PatientBean pb);

	List<PatientBean> printAilmentDetails(String userId);

	List<DoctorBean> PrintSpecificDoctor(DoctorBean ds);

	String requestforAppointment(String doctorID, Date appointmentDate, String userID);

}
