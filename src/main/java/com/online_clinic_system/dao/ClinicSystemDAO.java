package com.online_clinic_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.utility.DatabaseConnection;

public class ClinicSystemDAO implements ClinicSystemDAOInterface {

	@Override
	public String validateLogin(CredentialsBean userCredentialSet) {
		// TODO Auto-generated method stub
		String ss = null;
		try {
			
			Connection con=DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select USERTYPE from OCS_TBL_User_Credentials where USERID = ? and PASSWORD = ?");
			ps.setString(1,userCredentialSet.getUserID());
			ps.setString(2,userCredentialSet.getPassword());
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				ss = res.getString(1);
			}
		}
		catch(Exception e)
		{
		  System.out.println("User not found and it is Exception !!");
		}
			
			

				return ss;
	}

}
