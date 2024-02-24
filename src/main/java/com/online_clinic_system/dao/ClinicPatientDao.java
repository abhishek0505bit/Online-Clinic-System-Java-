package com.online_clinic_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;
import com.online_clinic_system.utility.DatabaseConnection;
import com.online_clinic_system.utility.IDGenerator;

public class ClinicPatientDao implements ClinicPatientDaoInterface {

	@Override
	public boolean insertUserCredentialDao(CredentialsBean newUserCredentials) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO OCS_TBL_User_Credentials (USERID, PASSWORD, USERTYPE, LOGINSTATUS) VALUES (?, ?, 'P', 1)";
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	 preparedStatement.setString(1, newUserCredentials.getUserID());
             preparedStatement.setString(2, newUserCredentials.getPassword());
            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            return rowsUpdated > 0;
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
		
	}

	@Override
	public boolean insertPatientDetailDao(PatientBean patientDetails) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO OCS_TBL_Patients (PATIENTID,USERID,APPOINTMENT_DATE, AILMENT_TYPE, AILMENT_DETAILS, DIAGNOSIS_HISTORY) VALUES (?, ?, ?, ?, ?, ?)";
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	 preparedStatement.setString(1, patientDetails.getPatientID());
        	 preparedStatement.setString(2, patientDetails.getUserID());
             preparedStatement.setDate(3, new java.sql.Date(patientDetails.getAppointmentDate().getTime()));
             preparedStatement.setString(4, patientDetails.getAilmentType());
             preparedStatement.setString(5, patientDetails.getAilmentDetails());
             preparedStatement.setString(6, patientDetails.getDiagnosisHistory());
            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            return rowsUpdated > 0;
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public String addAilmentDetailDao(PatientBean pb) {
		// TODO Auto-generated method stub
		String sql = "update OCS_TBL_Patients set AILMENT_DETAILS = ? where userid = ?";
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	 preparedStatement.setString(1, pb.getAilmentDetails());
             preparedStatement.setString(2, pb.getUserID());
            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            if(rowsUpdated == 1)
            	return "Success";
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "unsuccessfull";
	}

	@Override
	public List<PatientBean> PrintingAlinmentDetails(String userId) {
		// TODO Auto-generated method stub
		
		List<PatientBean> ll = new ArrayList<PatientBean>();
		String sql = "select AILMENT_DETAILS from OCS_TBL_Patients where USERID = ?";
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	 preparedStatement.setString(1, userId);
            // Execute the update query
            ResultSet res = preparedStatement.executeQuery();

			if(res.next()) {

				PatientBean bs = new PatientBean();

				bs.setAilmentDetails(res.getString(1));

				ll.add(bs);
			}

            // Check if the update was successful
            
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return ll;
	}

	@Override
	public List<DoctorBean> PrintingAlinmentDetails(DoctorBean ds) {
		// TODO Auto-generated method stub
		List<DoctorBean> ll = new ArrayList<DoctorBean>();
		String sql = "select DOCTORNAME from OCS_TBL_Doctor where SPECIALIZATION=?";
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	 preparedStatement.setString(1,ds.getSpecialization());
            // Execute the update query
            ResultSet res = preparedStatement.executeQuery();

			while(res.next()) {

				DoctorBean bs = new DoctorBean();

				bs.setDoctorName(res.getString(1));

				ll.add(bs);
			}

            // Check if the update was successful
            
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return ll;
	}

	@Override
	public String requestforAppointment(String doctorID, Date appointmentDate,String userID) {
		// TODO Auto-generated method stub
		try {
			
        	Connection con=DatabaseConnection.getConnection();
        	String checkExistingAppointmentQuery = "SELECT APPOINTMENTID FROM OCS_TBL_Appointment WHERE DOCTORID = ? AND APPOINTMENT_DATE = ?";
            PreparedStatement checkExistingAppointmentStatement = con.prepareStatement(checkExistingAppointmentQuery);
            checkExistingAppointmentStatement.setString(1, doctorID);
            checkExistingAppointmentStatement.setDate(2, new java.sql.Date(appointmentDate.getTime()));
            // Execute the update query
            ResultSet existingAppointmentResult = checkExistingAppointmentStatement.executeQuery();
            

			

            // Check if the update was successful
            if (existingAppointmentResult.next()) {
                return "Doctor is busy on the selected date. Please choose another date.";
            } else {
                // Insert the appointment record
            	String appId  = IDGenerator.generateCustomID("ai", 6);
                String insertAppointmentQuery = "INSERT INTO OCS_TBL_Appointment (DOCTORID, APPOINTMENT_DATE,APPOINTMENTID,PATIENTID) VALUES (?, ?, ?,?)";
                PreparedStatement insertAppointmentStatement = con.prepareStatement(insertAppointmentQuery);
                insertAppointmentStatement.setString(1, doctorID);
                insertAppointmentStatement.setDate(2, new java.sql.Date(appointmentDate.getTime()));
                insertAppointmentStatement.setString(3, appId);
                insertAppointmentStatement.setString(4, userID);
                insertAppointmentStatement.executeUpdate();

                return "Appointment booked successfully.";
            }
        	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "error";
	
	}

}
