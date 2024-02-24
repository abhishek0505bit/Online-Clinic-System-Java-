package com.online_clinic_system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.DoctorBean;

public interface ClinicReporterServiceInterface {

	ArrayList<DoctorBean> viewAllAvailableDoctorsS(Date date);

	List<DoctorBean> intimateAdminS(int status, Date date);

	

}
