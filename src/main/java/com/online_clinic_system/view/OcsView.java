package com.online_clinic_system.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import com.online_clinic_system.utility.*;

import com.online_clinic_system.controller.ClinicAdminController;
import com.online_clinic_system.controller.ClinicAdminControllerInterface;
import com.online_clinic_system.controller.ClinicReporterController;
import com.online_clinic_system.controller.ClinicReporterControllerInterface;
import com.online_clinic_system.controller.ClinicSystemController;
import com.online_clinic_system.controller.ClinicSystemControllerInterface;
import com.online_clinic_system.controller.ClinicPatientControllerInterface;
import com.online_clinic_system.controller.ClinicPatientController;
import com.online_clinic_system.entity.CredentialsBean;
import com.online_clinic_system.entity.PatientBean;


public class OcsView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Authentication authentication = new AuthenticationImpl();
        CredentialsBean loggedInUser = null;

        while (true) {
            System.out.println("**************Main Menu************");
            System.out.println("1. Sign In (Log In)");
            System.out.println("2. Sign Up (Register)");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Sign In
                    System.out.println("Enter User ID: ");
                    String userID = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();

                    CredentialsBean credentialsBean = new CredentialsBean();
                    credentialsBean.setUserID(userID);
                    credentialsBean.setPassword(password);

                    String role = authentication.login(credentialsBean);

                    if (role.equals("INVALID")) {
                        System.out.println("Invalid credentials. Please try again.");
                    } else {
                        loggedInUser = credentialsBean;
                        System.out.println("Login successful. Welcome, " + role + "!");
                        handleUserMenu(role, loggedInUser, authentication, sc);
                    }
                    break;

                case 2:
                    // Sign Up (Implement sign-up logic if needed)
                	 // Sign Up
                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("************** Patient Sign Up ************");
                    System.out.println("Enter User ID: ");
                    String newUserID = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String newPassword = sc.nextLine();
                    Date appointmentDate = null;
                    while (appointmentDate == null) {
                        try {
                            System.out.println("Enter Appointment Date (yyyy-MM-dd): ");
                            String dateStr = sc.nextLine();
                            appointmentDate = dateFormat.parse(dateStr);
                        } catch (java.text.ParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
                        }
                    }
                    System.out.println("Enter Ailment Type: ");
                    String ailmentType = sc.nextLine();
                    System.out.println("Enter Ailment Details: ");
                    String ailmentDetails = sc.nextLine();
                    System.out.println("Enter Diagnosis History: ");
                    String diagnosisHistory = sc.nextLine();

                    // Create a CredentialsBean for the user
                    CredentialsBean newUserCredentials = new CredentialsBean();
                    newUserCredentials.setUserID(newUserID);
                    newUserCredentials.setPassword(newPassword);

                    // Input and format appointment date
                 
                    // Create a PatientBean with the provided details
                    PatientBean patientDetails = new PatientBean();
                    patientDetails.setPatientID(newUserID);
                    patientDetails.setUserID(newUserID);
                    patientDetails.setAppointmentDate(appointmentDate);
                    patientDetails.setAilmentType(ailmentType);
                    patientDetails.setAilmentDetails(ailmentDetails);
                    patientDetails.setDiagnosisHistory(diagnosisHistory);
                    ClinicPatientControllerInterface patientController = new ClinicPatientController();
                    // Try to insert user credentials into the database
                    boolean credentialsInserted = patientController.insertUserCredentials(newUserCredentials);
                    

                    if (credentialsInserted) {
                        // Try to insert patient details into the database
                        boolean patientDetailsInserted = patientController.insertPatientDetails(patientDetails);

                        if (patientDetailsInserted) {
                            System.out.println("Patient Sign Up successful. You can now log in as a patient.");
                        } else {
                            System.out.println("Patient Details insertion failed. Please try again.");
                        }
                    } else {
                        System.out.println("User credentials insertion failed. Please try again.");
                    }
                    break;


                case 3:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private static void handleUserMenu(String role, CredentialsBean loggedInUser, Authentication authentication, Scanner sc) {
        boolean continueMenu = true;

        while (continueMenu) {
            switch (role) {
                case "A":
                    // Admin Menu
                    while (continueMenu) {
                        System.out.println("************** Admin Main Menu ************");
                        // Add admin-specific menu options here
                        System.out.println("Press 1 to add doctor details");
						System.out.println("Press 2 to modify doctor details");
						System.out.println("Press 3 to delete doctor details");
						System.out.println("Press 4 to view doctor details");
						System.out.println("Press 5 to LOG OUT from this system");
                        System.out.println("Enter your choice: ");
                        int adminChoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character
                    
						ClinicAdminControllerInterface adminController = new ClinicAdminController();
                        switch (adminChoice) {
                            case 1:
                                // Run Admin Method (Implement admin method logic)
                            	adminController.addDoctor();
                            	
                                break;
                            case 2:
								adminController.modifydoctordetails();
								break;
							case 3:
								adminController.deletedoctordetails();
								break;
							case 4:
								adminController.viewdoctordetails();
								break;
                            case 5:
                                // Logout
                                boolean loggedOut = authentication.logout(loggedInUser.getUserID());

                                if (loggedOut) {
                                    System.out.println("Logout successful. Goodbye, Admin!");
                                    continueMenu = false; // Exit the admin menu
                                } else {
                                    System.out.println("Logout failed. Please try again.");
                                }
                                break;

                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    }
                    break;

                case "R":
                    // Reporter Menu
                    while (continueMenu) {
                        System.out.println("************** Reporter Main Menu ************");
                        // Add reporter-specific menu options here
			        	 System.out.println("press 1 to View All Available Doctors ");
						 System.out.println("press 2 to Intimate Admin");
						 System.out.println("press 3 to Logout");
						 System.out.println("enter your choice");
						
						 ClinicReporterControllerInterface reporterController = new ClinicReporterController();
                        int reporterChoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character

                        switch (reporterChoice) {
                            case 1: reporterController.viewAllAvailableDoctorsC();
                                // Run Reporter Method (Implement reporter method logic)
                                break;
                                
                            
                            case 2: reporterController.intimateAdminC();
                                break;
                            
                            case 3: 
                                // Logout
                                boolean loggedOut = authentication.logout(loggedInUser.getUserID());

                                if (loggedOut) {
                                    System.out.println("Logout successful. Goodbye, Reporter!");
                                    continueMenu = false; // Exit the reporter menu
                                } else {
                                    System.out.println("Logout failed. Please try again.");
                                }
                                break;

                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    }
                    break;

                case "P":
                    // Patient Menu
                    while (continueMenu) {
                        System.out.println("************** Patient Main Menu ************");
                        // Add patient-specific menu options here
                        System.out.println("Press 1 to add/modify ailment details");
						System.out.println("Press 2 to view ailment details");
						System.out.println("Press 3 to view List Of Doctors");
						System.out.println("Press 4 to request for an appointment");
						System.out.println("Press 5 to log out");
                        System.out.println("Enter your choice: ");
                        int patientChoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character
                        ClinicPatientControllerInterface patientController = new ClinicPatientController();
                   
                        switch (patientChoice) {
                            case 1: patientController.addAilmentDetailsView(loggedInUser.getUserID());
                                // Run Patient Method (Implement patient method logic)
              
                                break;
                            case 2: patientController.viewAilmentDetailsView(loggedInUser.getUserID());
                                // Run Patient Method (Implement patient method logic)
                            	
                                System.out.println("Patient Method feature is not implemented.");
                                break;
                                
                            case 3: patientController.viewListOfDoctorView(loggedInUser.getUserID());
                            // Run Patient Method (Implement patient method logic)
                    
                            break;
                            case 4: patientController.requestAppointmentView(loggedInUser.getUserID());
                            // Run Patient Method (Implement patient method logic)
                    
                            break;

                            case 5:
                                // Logout
                                boolean loggedOut = authentication.logout(loggedInUser.getUserID());

                                if (loggedOut) {
                                    System.out.println("Logout successful. Goodbye, Patient!");
                                    continueMenu = false; // Exit the patient menu
                                } else {
                                    System.out.println("Logout failed. Please try again.");
                                }
                                break;

                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid user role.");
                    continueMenu = false; // Exit the menu
                    break;
            }
        }
    }
}



















//public class OcsView {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		Scanner sc=new Scanner(System.in);
//		String ss="y";
//		while(ss.equals("y")) 
//		{
//				System.out.println("**************Main Menu************");
//				System.out.println("press 1 to Signin");
//				System.out.println("press 2 to SignUp");
//						
//				System.out.println("enter your choice");
//				int c=sc.nextInt();
//				
//				ClinicSystemControllerInterface clinicController = new ClinicSystemController();
//				
//				switch(c) 
//				{
//				case 1 : String result = clinicController.SignInClinic();
//				         
//				         if(result.equals("A"))
//				         {
//				        	 String adminspage = "admin";
//				        	 String as = "y";
//								while(adminspage.equals("admin") && as.equals("y"))
//								{
//									System.out.println("****************Welcome to Admin Module*******************");
//									System.out.println("Press 1 to add doctor details");
//									System.out.println("Press 2 to modify doctor details");
//									System.out.println("Press 3 to delete doctor details");
//									System.out.println("Press 4 to view doctor details");
//									System.out.println("Press 5 to view patient by date ");
//									System.out.println("Press 6 to LOG OUT from this system");
//									int AdminChoice = sc.nextInt();
//									ClinicAdminControllerInterface adminController = new ClinicAdminController();
//									
//	//								//stat of switch case of admin
//									switch (AdminChoice) 
//									{
//										case 1: adminController.addDoctor();
//											break;
//										case 2:
//											adminController.modifydoctordetails();
//											break;
//										case 3:
//											adminController.deletedoctordetails();
//											break;
//										case 4:
//											adminController.viewdoctordetails();
//											break;
//										case 5 :
//											adminController.viewpatientsbydate();
//											break;
//										
//										case(6):System.out.println("Logout Successfull");
//											adminspage = "logout";
//											break;
//										
//										default:
//											System.out.println("Wrong Choice!!!! Please select the appropriate one");
//											break;
//									
//								    } // end of switch case of admin
//									if(adminspage.equals("admin")) {
//									    System.out.println("Do you wish to continue in  Admin Panel ???? If yes press Y or y");
//										as = sc.next();
//									    }
//									    else {
//									    	break;
//									    }
//				        	 
//				                 } // end of while loop of admin main
//				        } // end of admin condition
//				         
//				         //reporter
//				         if(result.equals("R")) 
//				         {
//				        	 
//				        	 String reporterspage = "reporter";
//				        	 String s = "y";
//								while(reporterspage.equals("reporter") && s.equals("y") ) 
//								{
//				        	 
//						        	 System.out.println("************** Reporter Main Menu ************");
//						        	 System.out.println("press 1 to View All Available Doctors ");
//									 System.out.println("press 2 to Intimate Admin");
//									 System.out.println("press 3 to Logout");
//									 System.out.println("enter your choice");
//									 int ReporterChoice=sc.nextInt();
//									 
//									 ClinicReporterControllerInterface reporterController = new ClinicReporterController();
//									 switch(ReporterChoice) {
//						        	    case 1: reporterController.viewAllAvailableDoctorsC();
//							        	 break;
//						        	    case 2: reporterController.intimateAdminC();
//							        	 break;
//						        	    case 3: System.out.println("Logout Successfull");
//						        	    	reporterspage="logout";
//						        	    break;
//						        	    
//						        	    
//						        	    }
//									    if(reporterspage.equals("reporter")) {
//									    System.out.println("Do you wish to continue in  Reporter Panel ???? If yes press Y or y");
//										s = sc.next();
//									    }
//									    else {
//									    	break;
//									    }
//									 
//				                }
//				         }
//		//		         else 
//		//		         {
//		//		        	 System.out.println("************** Patient Main Menu ************");
//		//						System.out.println("press 1 to Add Patient Details ");
//		//						System.out.println("press 2 to Edit Patient Details");
//		//						System.out.println("press 3 to View Patient Details");
//		//						System.out.println("press 4 to View List of Doctors");
//		//						System.out.println("press 5 to Request for Appointment");
//		//						System.out.println("press 6 to Delete Food Details");
//		//						System.out.println("press 7 to Logout");
//		//								
//		//						System.out.println("enter your choice");
//		//						int patientChoice=sc.nextInt();
//		//		        	 ClinicPatientControllerInterface patientController = new ClinicPatientController();
//		//		        	 
//		//		        	    switch(patientChoice) {
//		//		        	    case 1: patientController.addAilmentDetailsView();
//		//			        	 break;
//		//		        	    case 2: patientController.modifyAilmentDetailsView();
//		//			        	 break;
//		//		        	    case 3: patientController.viewAilmentDetailsView();
//		//			        	 break;
//		//		        	    
//		//		        	    }
//		//		  
//		//		        	 
//		//		         }
//		
//
//	             }
//
//          }
//}}