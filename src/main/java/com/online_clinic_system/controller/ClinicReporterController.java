package com.online_clinic_system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.online_clinic_system.service.ClinicReporterService;
import com.online_clinic_system.service.ClinicReporterServiceInterface;
import com.online_clinic_system.entity.DoctorBean;

public class ClinicReporterController implements ClinicReporterControllerInterface {
   
//	ArrayList<DoctorBean> viewAllAvailableDoctors(Date date)
	@Override
	public void viewAllAvailableDoctorsC() 
	{
		// TODO Auto-generated method stub
	 ClinicReporterServiceInterface reporterService = new ClinicReporterService();
	 Scanner scanner = new Scanner(System.in);

     // Prompt the user to enter a date
     System.out.print("Enter a date (YYYY-MM-DD): ");
     String dateInput = scanner.nextLine();

     // Parse the user input to a Date object
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date = null;
	     try {
	         date = dateFormat.parse(dateInput);
	     } catch (ParseException e) {
	         System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
	         System.exit(1);
	     }
	     
	 ArrayList<DoctorBean> viewAll = reporterService.viewAllAvailableDoctorsS(date);
		 for (DoctorBean doctor : viewAll) {
	         // Print or perform operations on each doctor
	         System.out.println("Doctor ID: " + doctor.getDoctorID());
	         System.out.println("Doctor Name: " + doctor.getDoctorName());
	         // Print other doctor-related information
	     }
	 
	}

	@Override
	public void intimateAdminC() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		// Get the status (0 for available, 1 for busy) from the user
        System.out.print("Enter status (0 for available, 1 for busy): ");
        int status = scanner.nextInt();

        // Get the date input from the user
        System.out.print("Enter date (yyyy-MM-dd): ");
        scanner.nextLine(); // Consume the newline character
        String dateStr = scanner.nextLine();

        // Parse the user-provided date string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        ClinicReporterServiceInterface reporterService = new ClinicReporterService();
        // Get and print doctors based on the user's input
        List<DoctorBean> doctors = reporterService.intimateAdminS(status, date);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println((status == 1 ? "Busy" : "Available") + " Doctors:");
            for (DoctorBean doctor : doctors) {
                System.out.println("Doctor ID: " + doctor.getDoctorID());
                System.out.println("Doctor Name: " + doctor.getDoctorName());
                System.out.println("-------------------------");
            }
        }
		
	}

}
