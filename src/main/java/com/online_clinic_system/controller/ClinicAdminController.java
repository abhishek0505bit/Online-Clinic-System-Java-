package com.online_clinic_system.controller;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.online_clinic_system.entity.DoctorBean;
import com.online_clinic_system.entity.AppointmentBean;
import com.online_clinic_system.entity.PatientBean;
import com.online_clinic_system.service.ClinicAdminService;
import com.online_clinic_system.service.ClinicAdminServiceInterface;
import com.online_clinic_system.utility.IDGenerator;

public class ClinicAdminController implements ClinicAdminControllerInterface {
   private ClinicAdminServiceInterface adminService = new ClinicAdminService();
   private Scanner scanner = new Scanner(System.in);
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public void addDoctor() {
	    
	    
	    System.out.println("Enter DOCTORNAME");
	    String DOCTORNAME = scanner.next();
	    
	    String DOCTORID = IDGenerator.generateCustomID(DOCTORNAME.substring(0, 2), 6);

	    Date DATEOFBIRTH = null;
	    while (DATEOFBIRTH == null) {
	        System.out.println("Enter DATEOFBIRTH (yyyy-MM-dd):");
	        String dobInput = scanner.next();
	        try {
	            DATEOFBIRTH = dateFormat.parse(dobInput);
	        } catch (ParseException e) {
	            System.out.println("Invalid date format. Use yyyy-MM-dd.");
	        }
	    }

	    Date DATEOFJOINING = null;
	    while (DATEOFJOINING == null) {
	        System.out.println("Enter DATEOFJOINING (yyyy-MM-dd):");
	        String dojInput = scanner.next();
	        try {
	            DATEOFJOINING = dateFormat.parse(dojInput);
	        } catch (ParseException e) {
	            System.out.println("Invalid date format. Use yyyy-MM-dd.");
	        }
	    }

	    System.out.println("Enter GENDER");
	    String GENDER = scanner.next();

	    System.out.println("Enter QUALIFICATION");
	    String QUALIFICATION = scanner.next();

	    System.out.println("Enter SPECIALIZATION");
	    String SPECIALIZATION = scanner.next();

	    int YEARSOFEXPERIENCE = 0;
	    while (YEARSOFEXPERIENCE <= 0) {
	        System.out.println("Enter YEARSOFEXPERIENCE (must be greater than 0):");
	        if (scanner.hasNextInt()) {
	            YEARSOFEXPERIENCE = scanner.nextInt();
	        } else {
	            System.out.println("Invalid input. Please enter a valid number.");
	            scanner.next(); // Consume invalid input
	        }
	    }

	    System.out.println("Enter STREET");
	    String STREET = scanner.nextLine();
	    scanner.nextLine();//consume newLine character
	    System.out.println("Enter LOCATION");
	    String LOCATION = scanner.nextLine();
	    System.out.println("Enter CITY");
	    String CITY = scanner.next();
        
	    System.out.println("Enter STATE");
	    String STATE = scanner.nextLine();
	    scanner.nextLine();//consume newLine character
	    System.out.println("Enter PINCODE");
	    String PINCODE = scanner.next();

	    System.out.println("Enter CONTACTNUMBER");
	    String CONTACTNUMBER = scanner.next();

	    System.out.println("Enter EMAILID");
	    String EMAILID = scanner.next();

	  

	    DoctorBean doctorDetails = new DoctorBean();
	    doctorDetails.setDoctorID(DOCTORID);
	    doctorDetails.setDoctorName(DOCTORNAME);
	    doctorDetails.setDateOfBirth(DATEOFBIRTH);
	    doctorDetails.setDateOfJoining(DATEOFJOINING);
	    doctorDetails.setGender(GENDER);
	    doctorDetails.setQualification(QUALIFICATION);
	    doctorDetails.setSpecialization(SPECIALIZATION);
	    doctorDetails.setYearsOfExperience(YEARSOFEXPERIENCE);
	    doctorDetails.setStreet(STREET);
	    doctorDetails.setLocation(LOCATION);
	    doctorDetails.setCity(CITY);
	    doctorDetails.setState(STATE);
	    doctorDetails.setPincode(PINCODE);
	    doctorDetails.setContactNumber(CONTACTNUMBER);
	    doctorDetails.setEmailID(EMAILID);

	    try {
	        String res = adminService.addDoctorS(doctorDetails);
	        if (res.equals("success")) {
	            System.out.println("Profile Created Successfully");
	        } else {
	            System.out.println("Oops! Profile Creation Failed");
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred while adding the doctor: " + e.getMessage());
	    } 
	}


	@Override
	public void modifydoctordetails() {
		// TODO Auto-generated method stub
		
		System.out.print("Enter Doctor ID to modify: ");
        String doctorId = scanner.nextLine();

        // Retrieve the current doctor details
        DoctorBean currentDoctor = adminService.getDoctorById(doctorId);

        if (currentDoctor == null) {
            System.out.println("Doctor not found.");
        }
        System.out.println("Current Doctor Details:");
        System.out.println("Doctor ID: " + currentDoctor.getDoctorID());
        System.out.println("Doctor Name: " + currentDoctor.getDoctorName());
        System.out.println("Email ID: " + currentDoctor.getEmailID());
        System.out.println("Contact Number: " + currentDoctor.getContactNumber());
        
     
        // Ask which field to edit
        System.out.println("Select a field to edit:");
        System.out.println("1. Email ID");
        System.out.println("2. Contact Number");
        System.out.println("3. Cancel (No edits)");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.print("Enter new Email ID: ");
                String newEmail = scanner.nextLine();
                currentDoctor.setEmailID(newEmail);
                break;
            case 2:
                System.out.print("Enter new Contact Number: ");
                String newContactNumber = scanner.nextLine();
                currentDoctor.setContactNumber(newContactNumber);
                break;
            case 3:
                System.out.println("No edits made.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        if(adminService.modifyDoctorS(currentDoctor))
        {
        	System.out.println("Successfully Edited");
        	
        }
        else {
        	System.out.println("Unable Edited");
        }
	}

	@Override
	public void deletedoctordetails() {
		// TODO Auto-generated method stub
		try {
            System.out.print("Enter Doctor ID to remove: ");
            String doctorID = scanner.nextLine();

             

            if (adminService.removeDoctorS(doctorID)) {
                System.out.println("Doctor removed successfully.");
            } else {
                System.out.println("Doctor not found or removal failed.");
            }

        

        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            // You might want to handle the exception differently
          
        }
		
	}

	@Override
	public void viewdoctordetails() {
		// TODO Auto-generated method stub
		
		try {
            // Call the service to retrieve the list of doctors
            ArrayList<DoctorBean> doctors = adminService.viewAllDoctors();

            // Check if the list is empty
            if (doctors.isEmpty()) {
                System.out.println("No doctors found.");
            } else {
                System.out.println("List of Doctors:");

                // Iterate through the list and print each doctor's details
                for (DoctorBean doctor : doctors) {
                	System.out.println("************************************");
                    System.out.println("Doctor ID: " + doctor.getDoctorID());
                    System.out.println("Doctor Name: " + doctor.getDoctorName());
                    System.out.println("Date of Birth: " + doctor.getDateOfBirth());
                    System.out.println("Date of Joining: " + doctor.getDateOfJoining());
                    System.out.println("Gender: " + doctor.getGender());
                    System.out.println("Qualification: " + doctor.getQualification());
                    System.out.println("Specialization: " + doctor.getSpecialization());
                    System.out.println("Years of Experience: " + doctor.getYearsOfExperience());
                    System.out.println("Street: " + doctor.getStreet());
                    System.out.println("Location: " + doctor.getLocation());
                    System.out.println("City: " + doctor.getCity());
                    System.out.println("State: " + doctor.getState());
                    System.out.println("Pincode: " + doctor.getPincode());
                    System.out.println("Contact Number: " + doctor.getContactNumber());
                    System.out.println("Email ID: " + doctor.getEmailID());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            // Handle the exception
            System.out.println("Something went wrong ");;
            // You might want to handle the exception differently
        }
		
	}


	@Override
	public void viewpatientsbydate() {
		// TODO Auto-generated method stub
		try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            System.out.print("Enter the date (yyyy-MM-dd): ");

            String dateStr = scanner.nextLine();

 

            // Parse the user-entered date string into a Date object

            Date appointmentDate = dateFormat.parse(dateStr);

 

            // Call the service to retrieve patients and their appointments for the given date

            Map<PatientBean, AppointmentBean> patientAppointmentMap = adminService.viewPatientsByDateS(appointmentDate);

 

            if (patientAppointmentMap.isEmpty()) {

                System.out.println("No patients with appointments on " + dateFormat.format(appointmentDate));

            } else {

                System.out.println("Patients and Appointments on " + dateFormat.format(appointmentDate) + ":");

                for (Map.Entry<PatientBean, AppointmentBean> entry : patientAppointmentMap.entrySet()) {

                    PatientBean patient = entry.getKey();

                    AppointmentBean appointment = entry.getValue();

                    System.out.println("Patient ID: " + patient.getPatientID());

                    System.out.println("Appointment ID: " + appointment.getAppointmentID());

                    System.out.println("Appointment Time: " + appointment.getAppointmentTime());

                    System.out.println();

                }

            }

 

        } catch (Exception e) {

            // Handle the exceptions

            e.printStackTrace();

            // You might want to handle the exceptions differently

        }
		
	}

}
