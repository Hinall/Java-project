package com.digitalgis.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;

public class CustomMessages {
	public final static int UNIQUE_ID_VALID = 1;
	public final static int RESPONSE_MESSAGE_500 = 2;
	public final static int RESPONSE_MESSAGE_200 = 5;
	public final static int RESPONSE_MESSAGE_204 = 3;
	public final static int RESPONSE_MESSAGE_206 = 4;
	public final static int RESPONSE_MESSAGE_404 = 6;
	public final static int RESPONSE_MESSAGE_NULL = 7;
	public final static int RESPONSE_LOGIN_MESSAGE_200 = 8;

	public final static int RESPONSE_LOGIN_MESSAGE_401 = 9;
	public final static int RESPONSE_MESSAGE_NULL_MOBILE_OR_UNAME = 10;
	public final static int RESPONSE_MESSAGE_NULL_PASSWORD = 11;
	public final static int RESPONSE_MESSAGE_NULL_MOBORUNAME_PASS = 12;
	public final static int RESPONSE_SENDMAIL_MESSAGE_200 = 13;
	public final static int RESPONSE_SEND_NOT_MESSAGE_204 = 14;

	public final static int PASSWORD_CHANGED = 15;
	public final static int PASSWORD_NOT_MATCHED = 16;

	public final static int RESPONSE_NO_DATA_FOUND_WARD_NO_400 = 17;

	public final static int RESPONSE_IMAGE_NOT_FOUND = 18;

	public final static int DATA_UPDATED = 19;
	public final static int DATA_NOT_UPDATED = 20;
	public final static int RESPONSE_MESSAGE_DATA_NOT_INSERTED = 21;
	public final static int RESPONSE_MESSAGE_NO_JSON_400 = 22;
	public final static int RESPONSE_MESSAGE_FOR_PROPERTY_200 = 23;
	public final static int RESPONSE_MESSAGE_FOR_USER_REGISTRATION = 24;
	public final static int RESPONSE_MESSAGE_FOR_IMAGE_DOWNLOAD = 25;
	public final static int RESPONSE_MESSAGE_FOR_ALREADY_REGISTER_USER = 26;
	public final static int RESPONSE_MESSAGE_FOR_FORGET_PASS = 27;
	public final static int RESPONSE_MESSAGE_FOR_CONTAIN_NULL = 28;
	public final static int LOGOUT_SUCCESSFULLY = 29;
	public final static int USER_DOES_NOT_EXIST = 30;

	public final static int IMAGE_NOT_UPLOADED = 31;

	public final static int RESPONSE_MESSAGE_USER_DENIED = 32;

	public final static int RESPONSE_MESSAGE_FOR_USERNAME_OR_PASSWORD_INCORRECT = 33;

	public final static int UNSUPPORTED_DATA_FILE = 34;
	public final static int UNSUPPORTED_MILESTONE = 35;
	public final static int SPECIAL_CHARACHTER_NOT_ALLOWED = 36;
	public final static int SPECIAL_WORD_NOT_ALLOWED = 37;
	public final static int SPECIAL_WORD_OR_CHARACTER_NOT_ALLOWED = 38;

	public final static int USER_LOGOUT_SUCCESSFUL = 39;
	public final static int TOKEN_EXPIRED = 40;

	public final static String NOTFOUND = "{\"status\":\"failed\",\"message\":\"Not Found!\"}";
	
	/**
	 * validation message
	 */
	public final static int MAP_CORRECTION_TYPE_IS_INVALID = 41;
	public final static int REASON_OF_CONCESSION_IS_INVALID = 42;
	public final static int PROPERTY_TYPE_OF_CONSTRUCTION_IS_INVALID = 43;
	public final static int SITUATION_IS_INVALID = 44;
	public final static int YEAR_OF_CONSTRUCTION_IS_INVALID = 45;
	public final static int NUMBER_OF_WATER_CONNECTION_IS_INVALID = 46;
	public final static int SURVEY_RESULT_IS_INVALID = 47;
	public final static int MEASUREMENT_STATUS_IS_INVALID = 48;
	public final static int RESIDENTIAL_PURPOSE_PROPERTY_IS_INVALID = 49;
	public final static int TYPE_OF_USE_IS_INVALID = 50;
	public final static int TYPE_OF_TOILET_IS_INVALID = 51;
	public final static int COLLECTION_METHOD_IS_INVALID = 52;
	public final static int RESPONDENT_TITLE_IS_INVALID = 53;
	public final static int FATHER_OR_HUSBAND_TITLE_IS_INVALID = 54;
	public final static int OWNER_NAME_TITLE_IS_INVALID = 55;
	public final static int JOINT_OWNER_TITLE_IS_INVALID = 56;
	public final static int FLOOR_NO_IS_INVALID = 57;
	public final static int MEASUREMENT_TYPE_OF_CONSTRUCTION_IS_INVALID = 58;
	public final static int USAGE_TYPE_IS_INVALID = 59;
	public final static int OCCUPANCY_TYPE_IS_INVALID = 60;
	public final static int NATURE_OF_USE_IS_INVALID = 61;
	public final static int RESIDENTIAL_PURPOSE_PROPERTY_IS_MISSING = 62;
	public final static int PROPERTY_TYPE_OF_CONSTRUCTION_IS_MISSING = 63;
	public final static int YEAR_OF_CONSTRUCTION_IS_MISSING = 64;
	
	
	public final static int RESPONSE_DATA_IS_MISSING = 65;
	
	public final static int PROPERTY_OWNER_SHIP_IS_INVALID = 66;
	public final static int RESIDENTIAL_PURPOSE_PROPERTY_NO_MIX_IS_INVALID = 67;
	
	public final static int RESPONSE_PASSWORD_IS_INCORRECT=68;
	
	public final static int RESPONSE_CONTACT_IS_INCORRECT=69;
	public final static int RESPONSE_INVALID_REQUEST=70;

	// static JSONObject msg=new JSONObject();
	static Map<String, Object> msg = new HashMap<String, Object>();

	public static String getMessage(int value) {

		switch (value) {
		case UNIQUE_ID_VALID:
			msg.put("status", "failed");
			msg.put("message", "Identification is not valid.");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_500:
			msg.put("responseCode", 500);
			msg.put("status", "failed");
			msg.put("responseMessage", "Internal Server Error");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_204:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "No data available");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_206:
			msg.put("responseCode", 206);
			msg.put("data", null);
			msg.put("responseMessage", "Please Enter valid value.");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_404:
			msg.put("responseCode", 404);
			msg.put("data", null);
			msg.put("responseMessage", "File not found.");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_200:
			msg.put("responseCode", 200);
			msg.put("responseMessage", "success");
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_NULL:
			msg.put("status", "failed");
			msg.put("responseMessage", "Null not allowed.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_NULL_MOBILE_OR_UNAME:
			msg.put("status", "failed");
			msg.put("responseMessage", "Please enter username");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_NULL_PASSWORD:
			msg.put("status", "failed");
			msg.put("responseMessage", "Please enter password");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_LOGIN_MESSAGE_200:
			msg.put("status", "success");
			msg.put("responseMessage", "Login Successfully");
			msg.put("responseCode", 200);
			return new Gson().toJson(msg);

		case RESPONSE_LOGIN_MESSAGE_401:
			msg.put("status", "failed");
			msg.put("responseMessage", "Invalid user name/user id or password.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_NULL_MOBORUNAME_PASS:
			msg.put("status", "failed");
			msg.put("responseMessage", "Please enter username and password.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_SENDMAIL_MESSAGE_200:
			msg.put("status", "success");
			msg.put("responseMessage", "Email sent Successfully");
			msg.put("responseCode", 200);
			return new Gson().toJson(msg);

		case RESPONSE_SEND_NOT_MESSAGE_204:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Email Not Send");
			return new Gson().toJson(msg);
		case RESPONSE_IMAGE_NOT_FOUND:
			msg.put("responseCode", 401);
			msg.put("data", null);
			msg.put("responseMessage", "Image Not Found");
			return new Gson().toJson(msg);

		case PASSWORD_CHANGED:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "Your password has been reset successfully.");
			return new Gson().toJson(msg);

		case PASSWORD_NOT_MATCHED:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			//msg.put("responseMessage", "Current password does not match.");
			msg.put("responseMessage", "Old password entered is incorrect.");
			return new Gson().toJson(msg);

		case RESPONSE_NO_DATA_FOUND_WARD_NO_400:
			msg.put("status", "failed");
			msg.put("responseMessage", "No Data found.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_DATA_NOT_INSERTED:
			msg.put("status", "failed");
			msg.put("responseMessage", "Data Not Inserted");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case DATA_UPDATED:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "Your Data has been updated successfully.");
			return new Gson().toJson(msg);

		case DATA_NOT_UPDATED:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Your data is not updated.");
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_NO_JSON_400:
			msg.put("responseCode", 400);
			msg.put("status", "failed");
			msg.put("responseMessage", "Requested Parameter not Present");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_FOR_PROPERTY_200:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "Property Data Inserted successfully.");
			return new Gson().toJson(msg);
		case RESPONSE_MESSAGE_FOR_USER_REGISTRATION:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "User Data Inserted successfully.");
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_FOR_IMAGE_DOWNLOAD:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "Image Uploaded successfully.");
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_FOR_ALREADY_REGISTER_USER:
			msg.put("status", "failed");
			msg.put("responseMessage", "User is already registered.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_FOR_FORGET_PASS:
			msg.put("status", "failed");
			msg.put("responseMessage", "The specified account is not registered. Please contact your administrator.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_FOR_CONTAIN_NULL:
			msg.put("status", "failed");
			msg.put("responseMessage", "Space is not allowed in password");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case LOGOUT_SUCCESSFULLY:
			msg.put("status", "logout");
			msg.put("responseMessage", "Logout Successfully");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);

		case USER_DOES_NOT_EXIST:
			msg.put("status", "failed");
			msg.put("responseMessage", "User Does Not Exist");
			msg.put("responseCode", 501);
			return new Gson().toJson(msg);

		case IMAGE_NOT_UPLOADED:
			msg.put("status", "failed");
			msg.put("responseMessage", "Error in uploading");
			msg.put("responseCode", 501);
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_USER_DENIED:
			msg.put("responseCode", 201);
			msg.put("status", "success");
			msg.put("responseMessage", "User Access Denied");
			return new Gson().toJson(msg);

		case RESPONSE_MESSAGE_FOR_USERNAME_OR_PASSWORD_INCORRECT:
			msg.put("responseCode", 201);
			msg.put("status", "success");
			msg.put("responseMessage", "Mobile No or Password is incorrect");
			return new Gson().toJson(msg);

		case UNSUPPORTED_DATA_FILE:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Unsupported file selected, please select only  png , jpg , jpeg file");
			return new Gson().toJson(msg);

		case UNSUPPORTED_MILESTONE:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Unsupported file selected, please select only pdf , doc file");
			return new Gson().toJson(msg);

		case SPECIAL_CHARACHTER_NOT_ALLOWED:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Special Character is not allowed.");
			return new Gson().toJson(msg);

		case SPECIAL_WORD_NOT_ALLOWED:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Special words are not allowed.");
			return new Gson().toJson(msg);

		case SPECIAL_WORD_OR_CHARACTER_NOT_ALLOWED:
			msg.put("responseCode", 401);
			msg.put("status", "failed");
			msg.put("responseMessage", "Special words or characters are not allowed.");
			return new Gson().toJson(msg);

		case USER_LOGOUT_SUCCESSFUL:
			msg.put("responseCode", 200);
			msg.put("status", "success");
			msg.put("responseMessage", "User successfully logged out.");
			return new Gson().toJson(msg);

		case TOKEN_EXPIRED:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Unauthorized access. Token is expired.");
			return new Gson().toJson(msg);

		case MAP_CORRECTION_TYPE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Map correction type is invalid.");
			return new Gson().toJson(msg);
		case REASON_OF_CONCESSION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Reason of concession is invalid.");
			return new Gson().toJson(msg);
			
		case SITUATION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Situation is invalid.");
			return new Gson().toJson(msg);
			
		case PROPERTY_TYPE_OF_CONSTRUCTION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Property type of construction is invalid.");
			return new Gson().toJson(msg);
			
		case YEAR_OF_CONSTRUCTION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Year of construction is invalid.");
			return new Gson().toJson(msg);
			
		case NUMBER_OF_WATER_CONNECTION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "No. of water connection is invalid.");
			return new Gson().toJson(msg);
			
		case SURVEY_RESULT_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Survey result is invalid.");
			return new Gson().toJson(msg);
			
		case MEASUREMENT_STATUS_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Measurement status is invalid.");
			return new Gson().toJson(msg);
			
		case RESIDENTIAL_PURPOSE_PROPERTY_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Residential purpose property is invalid.");
			return new Gson().toJson(msg);
			
		case TYPE_OF_USE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Type of use is invalid.");
			return new Gson().toJson(msg);
			
		case TYPE_OF_TOILET_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Type of toilet is invalid.");
			return new Gson().toJson(msg);
			
		case COLLECTION_METHOD_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Collection method is invalid.");
			return new Gson().toJson(msg);
			
		case RESPONDENT_TITLE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Respondent title type is invalid.");
			return new Gson().toJson(msg);
			
		case FATHER_OR_HUSBAND_TITLE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Father/Husband title is invalid.");
			return new Gson().toJson(msg);
			
		case OWNER_NAME_TITLE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Owner name title is invalid.");
			return new Gson().toJson(msg);
			
		case JOINT_OWNER_TITLE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Joint owner title is invalid.");
			return new Gson().toJson(msg);
			
		case FLOOR_NO_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Floor no. is invalid.");
			return new Gson().toJson(msg);
			
		case MEASUREMENT_TYPE_OF_CONSTRUCTION_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Measurement type of construction is invalid.");
			return new Gson().toJson(msg);
			
		case USAGE_TYPE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Usage type is invalid.");
			return new Gson().toJson(msg);
			
		case OCCUPANCY_TYPE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Occupancy is invalid.");
			return new Gson().toJson(msg);
			
		case NATURE_OF_USE_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Nature of use is invalid.");
			return new Gson().toJson(msg);
			
		case RESIDENTIAL_PURPOSE_PROPERTY_IS_MISSING:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Residential purpose property is missing.");
			return new Gson().toJson(msg);
			
		case PROPERTY_TYPE_OF_CONSTRUCTION_IS_MISSING:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Property type of construction is missing.");
			return new Gson().toJson(msg);
			
		case YEAR_OF_CONSTRUCTION_IS_MISSING:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Year of construction is missing.");
			return new Gson().toJson(msg);
			
		case RESPONSE_DATA_IS_MISSING:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Property Data is missing.");
			return new Gson().toJson(msg);
			
		case PROPERTY_OWNER_SHIP_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Property owner ship is invalid");
			return new Gson().toJson(msg);
			
		case RESIDENTIAL_PURPOSE_PROPERTY_NO_MIX_IS_INVALID:
			msg.put("responseCode", 201);
			msg.put("status", "failed");
			msg.put("responseMessage", "Residential purpose property is invalid.");
			return new Gson().toJson(msg);
			
			
		case RESPONSE_PASSWORD_IS_INCORRECT:
			msg.put("status", "failed");
			msg.put("responseMessage", "Password should contain minimum 8 character with minimum one Uppercase character and one number and one special character");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);
			
		case RESPONSE_CONTACT_IS_INCORRECT:
			msg.put("status", "failed");
			msg.put("responseMessage", "Contact Number should be number only.");
			msg.put("responseCode", 401);
			return new Gson().toJson(msg);
			
		case RESPONSE_INVALID_REQUEST:
			msg.put("status", "failed");
			msg.put("responseMessage", "Invalid request");
			msg.put("responseCode", 202);
			return new Gson().toJson(msg);	
			
			
		default:
			return null;
		}
	}

	public static String getMessage(String data) {
		msg.put("responseCode", "201");
		msg.put("data", null);
		msg.put("responseMessage", data);
		return new Gson().toJson(msg);
	}

	public static JSONObject getMessage(Map<String, Object> data) {
		msg.put("responseCode", "200");
		msg.put("data", data);
		return new JSONObject(msg);
	}

}
