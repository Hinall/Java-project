package com.digitalgis.dao;

import org.springframework.stereotype.Service;

import com.digitalgis.model.User;
import com.digitalgis.model.UserModel;

@Service
public interface UserDao {

	User checkUserLoginDeatils(String userName);

	//Integer addUserToken(String username, String token);

	String getUserProfile(String json);

	String updateUserSettings(String json);

	String verifyUserToken(String token);

	String removeUserToken(String token);
	
	String registerNewUser(String json);

	String getWebUserProfile(String json);
	
	String getUserDetailsById(Integer userId);

	String checkUserNameIsExist(String json);

	String storeTempararyPassword(String json);

	String updateUserPassword(String json);

	Integer addWebUserToken(String username, String token);

	String add_update_role(String json);

	String delete_role(String json);

	String get_all_role(String json);

	String crud_usermanagement(String json);

	String getRollDetailsById(Integer rollId);

	String getAllSurveyor();

	String getUserDetailsByMail(String json);

	String updateRoleStatus(String json);

	String updateUserStatus(String json);

	String resetPassword(String json);

	String verifyOtp(String json);

	String get_User_Id_By_Mail(String json);

	String send_OTP(String json);

	String registerSurveyor(String json);

	String getUserProfileByUserId(String json);

}