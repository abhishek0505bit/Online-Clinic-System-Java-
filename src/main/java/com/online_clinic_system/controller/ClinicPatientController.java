package com.online_clinic_system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.PatientBean;
import com.online_clinic_system.service.ClinicPatientService;
import com.online_clinic_system.service.ClinicPatientServiceInterface;

public class ClinicPatientController implements ClinicPatientControllerInterface {
    
	@Override
	public void addAilmentDetailsView(String userId) {
		Scanner s = new Scanner(System.in);
        PatientBean pb = new PatientBean();
		System.out.println("Enter the Ailment details you want to add");

		String ailmentDetails = s.nextLine();

		pb.setAilmentDetails(ailmentDetails);

		pb.setUserID(userId);
		ClinicPatientServiceInterface patientService = new  ClinicPatientService();
		String result = patientService.addAilmentServiceDetails(pb);

		System.out.println(result);
		
		
	}

	@Override
	public void modifyAilmentDetailsView() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean insertUserCredentials(CredentialsBean newUserCredentials) {
		// TODO Auto-generated method stub
		ClinicPatientServiceInterface patientService = new  ClinicPatientService();
		return patientService.insertUserCredentialS(newUserCredentials);// return true
	}

	@Override
	public boolean insertPatientDetails(PatientBean patientDetails) {
		// TODO Auto-generated method stub
		ClinicPatientServiceInterface patientService = new  ClinicPatientService();
		return patientService.insertPatientDetailS(patientDetails);
	}

	@Override
	public void viewAilmentDetailsView(String userId) {
		// TODO Auto-generated method stub
		ClinicPatientServiceInterface patientService = new  ClinicPatientService();
		Scanner s = new Scanner(System.in);

		System.out.println("Your Ailment Details are");

		List<PatientBean> ll= patientService.printAilmentDetails(userId);

		for(PatientBean nn:ll) {

			System.out.println(nn.getAilmentDetails());

		}
		
	}

	@Override
	public void viewListOfDoctorView(String userID) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		System.out.println("Enter the doctor Specialization");

		String Specialization = s.next();

		DoctorBean ds = new DoctorBean();

		ds.setSpecialization(Specialization);
		ClinicPatientServiceInterface patientService = new  ClinicPatientService();
		List<DoctorBean> ll = patientService.PrintSpecificDoctor(ds);

		for(DoctorBean nn:ll) {

			System.out.println(nn.getDoctorName());

		}
	}

	@Override
	public void requestAppointmentView(String userID) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Doctor ID: ");
        String doctorID = sc.nextLine();
        
        // Input validation can be added here.
        
        System.out.println("Enter Appointment Date (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date appointmentDate = sdf.parse(dateStr);
            // Call the service method and pass the inputs
            ClinicPatientServiceInterface patientService = new  ClinicPatientService();
            String result = patientService.requestforAppointment(doctorID, appointmentDate,userID);
            
            // Return the result to the view or handle it as needed.
           System.out.println(result);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            
        } 
		
	}

}
