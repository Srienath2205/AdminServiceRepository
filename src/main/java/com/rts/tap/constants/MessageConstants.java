

package com.rts.tap.constants;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

public class MessageConstants {
	public static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MessageConstants.class);

	public static final String SUCCESS_MESSAGE = "Success";
	public static final String FAILURE_MESSAGE = "Failure";
	
	public static final String LOGIN_SUCCESS = "Login Success";
	public static final String LOGIN_NOT_FOUND = "Login credentials not found";
	
	public static final String OTP_SENT = "OTP sent to your email.";
	public static final String OTP_VERIFY = "OTP Verified";
	public static final String OTP_RESENT = "OTP Resend";
	
	public static final String EMAIL_EXIST = "Email Exist";
	public static final String EMPLOYEE_STATUS = "Employee INACTIVE";

	public static final String ORGIN = "http://localhost:3000";

	public static final String DELETE_SUCCESS = "Delete Success";
	public static final String DELETE_FAILURE = "Delete Failure";

	public static final String UPDATE_SUCCESS = "Update Success";
	public static final String UPDATE_FAILURE = "Update Failure";

	public static final String ORG_LOCATION_CREATED_SUCCESS = "Organization Location created successfully!";
	public static final String ORG_LOCATION_CREATION_FAILED = "Failed to create Organization Location!";
	public static final String ORG_LOCATION_UPDATED_SUCCESS = "Organization Location updated successfully!";
	public static final String ORG_LOCATION_UPDATE_FAILED = "Failed to update Organization Location!";
	public static final String ORG_LOCATION_DELETED_SUCCESS = "Organization Location deleted successfully!";
	public static final String ORG_LOCATION_DELETION_FAILED = "Failed to delete Organization Location!";
	public static final String ORG_LOCATION_NOT_FOUND = "Organization Location not found with ID: ";
	public static final String ORG_LOCATIONS_RETRIEVED_SUCCESS = "All Organization Locations retrieved successfully!";
	public static final String ORG_LOCATION_RETRIEVED_SUCCESS = "Organization Location retrieved successfully!";

	// Error Messages
	public static final String INTERNAL_SERVER_ERROR = "Internal server error occurred!";

}
