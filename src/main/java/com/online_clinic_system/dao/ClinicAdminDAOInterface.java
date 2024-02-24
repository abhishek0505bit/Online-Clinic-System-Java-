package com.online_clinic_system.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.online_clinic_system.entity.AppointmentBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public interface ClinicAdminDAOInterface {

	String addDoctorDao(DoctorBean doctorDetails);

	ArrayList<DoctorBean> viewAllDoctors();

	DoctorBean getDoctorById(String doctorId);

	boolean modifyDoctorDao(DoctorBean currentDoctor);

	boolean deleteDoctorDao(String doctorID);

	Map<PatientBean, AppointmentBean> viewPatientByDateDao(Date appointmentDate);

}
