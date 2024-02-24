package com.online_clinic_system.dao;

import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public interface ClinicPatientDaoInterface {

	boolean insertUserCredentialDao(CredentialsBean newUserCredentials);

	boolean insertPatientDetailDao(PatientBean patientDetails);

	String addAilmentDetailDao(PatientBean pb);

	List<PatientBean> PrintingAlinmentDetails(String userId);

	List<DoctorBean> PrintingAlinmentDetails(DoctorBean ds);

	String requestforAppointment(String doctorID, Date appointmentDate,String userID);

}
