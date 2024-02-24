package com.online_clinic_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.utility.DatabaseConnection;

public class ClinicReporterDAO implements ClinicReporterDAOInterface {

	@Override
	public ArrayList<DoctorBean> viewAllAvailableDoctorsD(Date date) {
		// TODO Auto-generated method stub
		 ArrayList<DoctorBean> result = new ArrayList<>();
		 String sql = "SELECT D.DOCTORID, D.DOCTORNAME, D.DATEOFJOINING, D.SPECIALIZATION FROM OCS_TBL_Doctor D " +
                 "LEFT JOIN OCS_TBL_Leave L ON D.DOCTORID = L.DOCTORID AND ? BETWEEN L.LEAVE_FROM AND L.LEAVE_TO " +
                 "WHERE L.DOCTORID IS NULL AND ? >= D.DATEOFJOINING";
		try
		{
			
			Connection con=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setDate(2, sqlDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) 
            {
                DoctorBean doctor = new DoctorBean();
                // Populate the DoctorBean with data from the result set
                doctor.setDoctorID(resultSet.getString("DOCTORID"));
                doctor.setDoctorName(resultSet.getString("DOCTORNAME"));
                doctor.setDateOfJoining(resultSet.getDate("DATEOFJOINING"));
                doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
                // ...

                result.add(doctor);
            }
            
		}
		catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return result;
	}

	@Override
	public List<DoctorBean> intimateAdminDao(int status, Date date) {
		List<DoctorBean> doctors = new ArrayList<>();

        // Define a SQL query to retrieve doctors based on status and date
        String sql= "SELECT d.DOCTORID, d.DOCTORNAME " +
                          "FROM OCS_TBL_Doctor d " +
                          "LEFT JOIN OCS_TBL_Leave l ON d.DOCTORID = l.DOCTORID " +
                          "WHERE (l.STATUS = ? AND ? BETWEEN l.LEAVE_FROM AND l.LEAVE_TO) " +
                          "OR (l.STATUS IS NULL)";
		try
		{
			
			Connection con=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			 // Set the status parameter in the SQL query
            preparedStatement.setInt(1, status);

            // Set the date parameter in the SQL query (formatted directly)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            preparedStatement.setString(2, dateFormat.format(date));

            // Execute the SQL query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and populate the list of doctors
            while (resultSet.next()) {
            	DoctorBean doctor = new DoctorBean();
                // Populate the DoctorBean with data from the result set
                doctor.setDoctorID(resultSet.getString(1));
                doctor.setDoctorName(resultSet.getString("DOCTORNAME"));
                // ...

                doctors.add(doctor);
            }
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		return doctors;
	}

}
