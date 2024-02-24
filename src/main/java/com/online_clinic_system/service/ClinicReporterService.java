package com.online_clinic_system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.online_clinic_system.dao.ClinicReporterDAO;
import com.online_clinic_system.dao.ClinicReporterDAOInterface;
import com.online_clinic_system.entity.DoctorBean;

public class ClinicReporterService implements ClinicReporterServiceInterface {

	@Override
	public ArrayList<DoctorBean> viewAllAvailableDoctorsS(Date date) {
		// TODO Auto-generated method stub
		ClinicReporterDAOInterface reporterDao = new ClinicReporterDAO();
		ArrayList<DoctorBean> viewAll = reporterDao.viewAllAvailableDoctorsD(date);
		return viewAll;
	}

	@Override
	public List<DoctorBean> intimateAdminS(int status, Date date) {
		// TODO Auto-generated method stub
		ClinicReporterDAOInterface reporterDao = new ClinicReporterDAO();
		return reporterDao.intimateAdminDao(status,date);
	}

	

}
