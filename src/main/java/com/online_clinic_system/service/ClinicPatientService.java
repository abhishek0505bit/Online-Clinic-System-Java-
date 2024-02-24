package com.online_clinic_system.service;

import java.util.Date;
import java.util.List;

import com.online_clinic_system.dao.ClinicPatientDao;
import com.online_clinic_system.dao.ClinicPatientDaoInterface;
import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public class ClinicPatientService implements ClinicPatientServiceInterface {

	@Override
	public boolean insertUserCredentialS(CredentialsBean newUserCredentials) {
		// TODO Auto-generated method stub
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.insertUserCredentialDao(newUserCredentials);
	}

	@Override
	public boolean insertPatientDetailS(PatientBean patientDetails) {
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.insertPatientDetailDao(patientDetails);
	}

	@Override
	public String addAilmentServiceDetails(PatientBean pb) {
		// TODO Auto-generated method stub
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.addAilmentDetailDao(pb);
	}

	@Override
	public List<PatientBean> printAilmentDetails(String userId) {
		// TODO Auto-generated method stub
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.PrintingAlinmentDetails(userId);
	}

	@Override
	public List<DoctorBean> PrintSpecificDoctor(DoctorBean ds) {
		// TODO Auto-generated method stub
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.PrintingAlinmentDetails(ds);
	}

	@Override
	public String requestforAppointment(String doctorID, Date appointmentDate,String userID) {
		// TODO Auto-generated method stub
		ClinicPatientDaoInterface patientDao = new ClinicPatientDao();
		return patientDao.requestforAppointment(doctorID,appointmentDate,userID);
		
	}

}
