package com.online_clinic_system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.online_clinic_system.entity.AppointmentBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public interface ClinicAdminServiceInterface {

	String addDoctorS(DoctorBean doctorDetails);
     
	ArrayList<DoctorBean> viewAllDoctors();

	DoctorBean getDoctorById(String doctorId);

	boolean modifyDoctorS(DoctorBean currentDoctor);

	boolean removeDoctorS(String doctorID);

	Map<PatientBean, AppointmentBean> viewPatientsByDateS(Date appointmentDate);

}
