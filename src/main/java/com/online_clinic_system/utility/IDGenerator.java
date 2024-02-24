package com.online_clinic_system.utility;
import java.util.Random;
public class IDGenerator {
	 
	    // Generate a custom DOCTORID or userID based on the specified criteria
	    public static String generateCustomID(String prefix, int length) {
	        StringBuilder customID = new StringBuilder(prefix);

	        Random random = new Random();
	        for (int i = prefix.length(); i < length; i++) {
	            customID.append(random.nextInt(10)); // Appending random digits
	            
	        }
            
	        return customID.toString();
	    }
	
}
