package com.online_clinic_system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.online_clinic_system.dao.ClinicAdminDAO;
import com.online_clinic_system.dao.ClinicAdminDAOInterface;
import com.online_clinic_system.entity.AppointmentBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;

public class ClinicAdminService implements ClinicAdminServiceInterface {
	@Override
	public String addDoctorS(DoctorBean doctorDetails) {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		String res = adminDao.addDoctorDao(doctorDetails);
		return res;
	}

	@Override
	public ArrayList<DoctorBean> viewAllDoctors() {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		ArrayList<DoctorBean> doctors = adminDao.viewAllDoctors();
		
		return doctors;
	}

	@Override
	public DoctorBean getDoctorById(String doctorId) {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		return adminDao.getDoctorById(doctorId);
	}

	@Override
	public boolean modifyDoctorS(DoctorBean currentDoctor) {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		return adminDao.modifyDoctorDao(currentDoctor);
	}

	@Override
	public boolean removeDoctorS(String doctorID) {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		return adminDao.deleteDoctorDao(doctorID);
	}

	@Override
	public Map<PatientBean, AppointmentBean> viewPatientsByDateS(Date appointmentDate) {
		// TODO Auto-generated method stub
		ClinicAdminDAOInterface adminDao = new ClinicAdminDAO();
		return adminDao.viewPatientByDateDao(appointmentDate);
	}

}
