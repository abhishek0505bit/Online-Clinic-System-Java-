package com.online_clinic_system.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.DoctorBean;

public interface ClinicReporterDAOInterface {

	ArrayList<DoctorBean> viewAllAvailableDoctorsD(Date date);

	List<DoctorBean> intimateAdminDao(int status, Date date);

}
