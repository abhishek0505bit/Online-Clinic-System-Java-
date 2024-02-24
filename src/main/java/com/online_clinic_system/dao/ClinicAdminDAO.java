package com.online_clinic_system.dao;

import com.online_clinic_system.entity.AppointmentBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;
import com.online_clinic_system.utility.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class ClinicAdminDAO implements ClinicAdminDAOInterface {
	@Override
	public String addDoctorDao(DoctorBean doctor){
		// TODO Auto-generated method stub
		// SQL query to insert a new doctor record
        String sql = "INSERT INTO OCS_TBL_Doctor (DOCTORID, DOCTORNAME, DATEOFBIRTH, DATEOFJOINING, GENDER, " +
                     "QUALIFICATION, SPECIALIZATION, YEARSOFEXPERIENCE, STREET, LOCATION, CITY, STATE, " +
                     "PINCODE, CONTACTNUMBER, EMAILID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Create a prepared statement
        try  {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
            // Set the parameters
            preparedStatement.setString(1, doctor.getDoctorID());
            preparedStatement.setString(2, doctor.getDoctorName());
            preparedStatement.setDate(3, new java.sql.Date(doctor.getDateOfBirth().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(doctor.getDateOfJoining().getTime()));
            preparedStatement.setString(5, doctor.getGender());
            preparedStatement.setString(6, doctor.getQualification());
            preparedStatement.setString(7, doctor.getSpecialization());
            preparedStatement.setInt(8, doctor.getYearsOfExperience());
            preparedStatement.setString(9, doctor.getStreet());
            preparedStatement.setString(10, doctor.getLocation());
            preparedStatement.setString(11, doctor.getCity());
            preparedStatement.setString(12, doctor.getState());
            preparedStatement.setString(13, doctor.getPincode());
            preparedStatement.setString(14, doctor.getContactNumber());
            preparedStatement.setString(15, doctor.getEmailID());

            // Execute the query and check the result
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                return "success";
            }
        } catch (Exception e) {
             System.out.println(e);
          }
		return "fail";
	}

	@Override
	public ArrayList<DoctorBean> viewAllDoctors() {
		// TODO Auto-generated method stub
	        ArrayList<DoctorBean> doctors = new ArrayList<>();

	        // SQL query to select all doctors
	        String sql = "SELECT * FROM OCS_TBL_Doctor";

	        // Create a prepared statement
	        try  {
	            // Execute the query
	        	Connection con=DatabaseConnection.getConnection();
	        	PreparedStatement preparedStatement = con.prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            // Iterate through the result set and create DoctorBean objects
	            while (resultSet.next()) {
	                DoctorBean doctor = new DoctorBean();
	                doctor.setDoctorID(resultSet.getString("DOCTORID"));
	                doctor.setDoctorName(resultSet.getString("DOCTORNAME"));
	                doctor.setDateOfBirth(resultSet.getDate("DATEOFBIRTH"));
	                doctor.setDateOfJoining(resultSet.getDate("DATEOFJOINING"));
	                doctor.setGender(resultSet.getString("GENDER"));
	                doctor.setQualification(resultSet.getString("QUALIFICATION"));
	                doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
	                doctor.setYearsOfExperience(resultSet.getInt("YEARSOFEXPERIENCE"));
	                doctor.setStreet(resultSet.getString("STREET"));
	                doctor.setLocation(resultSet.getString("LOCATION"));
	                doctor.setCity(resultSet.getString("CITY"));
	                doctor.setState(resultSet.getString("STATE"));
	                doctor.setPincode(resultSet.getString("PINCODE"));
	                doctor.setContactNumber(resultSet.getString("CONTACTNUMBER"));
	                doctor.setEmailID(resultSet.getString("EMAILID"));

	                doctors.add(doctor);
	            }
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }

	        return doctors;
	    
	}

	@Override
	public DoctorBean getDoctorById(String doctorId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM OCS_TBL_Doctor WHERE DOCTORID = ?";

		try  {
            // Execute the query
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setString(1, doctorId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create and return a DoctorBean object
                DoctorBean doctor = new DoctorBean();
                doctor.setDoctorID(resultSet.getString("DOCTORID"));
                doctor.setDoctorName(resultSet.getString("DOCTORNAME"));
                doctor.setEmailID(resultSet.getString("EMAILID"));
                doctor.setContactNumber(resultSet.getString("CONTACTNUMBER"));
                // Set other doctor details here...
                return doctor;
            }
            
		}
		catch(Exception e) {
        	System.out.println(e);
        }
		return null;
		
	}

	@Override
	public boolean modifyDoctorDao(DoctorBean doctor) {
		// TODO Auto-generated method stub
		 String sql = "UPDATE OCS_TBL_Doctor SET EMAILID = ?, CONTACTNUMBER = ? WHERE DOCTORID = ?";
		try  {
            // Execute the query
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setString(1, doctor.getEmailID());
            preparedStatement.setString(2, doctor.getContactNumber());
            preparedStatement.setString(3, doctor.getDoctorID());
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
	public boolean deleteDoctorDao(String doctorID) {
		String sql = "DELETE FROM OCS_TBL_Doctor WHERE DOCTORID = ?";
            // Execute the query
		try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, doctorID);
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
	public Map<PatientBean, AppointmentBean> viewPatientByDateDao(Date appointmentDate) {
		// TODO Auto-generated method stub
		Map<PatientBean, AppointmentBean> patientAppointmentMap = new HashMap<>();

		 

        // SQL query to retrieve patients and their appointments for the given date

        String sql = "SELECT P.*, A.* " +

                     "FROM OCS_TBL_Patient P " +

                     "LEFT JOIN OCS_TBL_Appointments A " +

                     "ON P.PATIENTID = A.PATIENTID " +

                     "WHERE A.APPOINTMENT_DATE = ?";
        
        try {
        	Connection con=DatabaseConnection.getConnection();
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
           
            // Execute the update query
            preparedStatement.setDate(1, new java.sql.Date(appointmentDate.getTime()));

            

            // Execute the query

            ResultSet resultSet = preparedStatement.executeQuery();

 

            while (resultSet.next()) {

                // Create PatientBean and AppointmentBean objects for each record

                PatientBean patient = new PatientBean();

                patient.setPatientID(resultSet.getString("PATIENTID"));

                patient.setUserID(resultSet.getString("USERID"));

                patient.setAppointmentDate(resultSet.getDate("APPOINTMENT_DATE"));

                patient.setAilmentType(resultSet.getString("AILMENT_TYPE"));

                patient.setAilmentDetails(resultSet.getString("AILMENT_DETAILS"));

                patient.setDiagnosisHistory(resultSet.getString("DIAGNOSIS_HISTORY"));

                // Set other patient details here...

 

                AppointmentBean appointment = new AppointmentBean();

                appointment.setAppointmentID(resultSet.getString("APPOINTMENTID"));

                appointment.setDoctorID(resultSet.getString("DOCTORID"));

                appointment.setPatientID(resultSet.getString("PATIENTID"));

                appointment.setAppointmentDate(resultSet.getDate("APPOINTMENT_DATE"));

                appointment.setAppointmentTime(resultSet.getString("APPOINTMENT_TIME"));

                // Set other appointment details here...

 

                // Add the patient and appointment to the map

                patientAppointmentMap.put(patient, appointment);

            }

        }
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
