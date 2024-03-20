package com.digitalgis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalgis.jwt.JwtProvider;
import com.digitalgis.jwt.JwtResponse;
import com.digitalgis.model.ResponseModel;
import com.digitalgis.model.UserModel;
import com.digitalgis.model.UserProfileModel;
import com.digitalgis.service.CommonService;
import com.digitalgis.service.UserService;
import com.digitalgis.utils.CustomMessages;
import com.digitalgis.utils.MailUtils;
import com.digitalgis.utils.RandomString;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@RestController
//@PropertySource("classpath:log4j.properties")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private Gson gson;

	@Autowired
	private MailUtils mailUtils;

	@Value("${sender.mail.id}")
	private String fromMail;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;

	@Value("${image.FilePath}")
	private String imageFilePath;

//	@Autowired
//	private ObjectMapper objectmapper;

//	@Autowired
//	private Gson gson;

	/**
	 * API For User Login through Web application
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */

	
	
	
	
	
	
	
	
	
	
	
	// WRITE A USED API ABOVE THIS COMMENTS
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ApiIgnore
	@ApiOperation(value = "User Login API which validates the and authenticates valid users through web")
	@RequestMapping(value = "/weblogin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> loginWebUser(@RequestBody String json) throws JSONException {

		String username = "";
		String password = "";
		try {
			JSONObject obj = new JSONObject(json);

			username = obj.getString("user_name").toString(); // pass json key as Username
			password = obj.getString("password").toString();
			System.out.println("username " + username + "  password " + password);

			// validation for checking weather its empty or not
			if ((username.equalsIgnoreCase(" ") || username.equalsIgnoreCase(""))
					&& (password.equalsIgnoreCase(" ") || password.equalsIgnoreCase(""))) {
				return ResponseEntity
						.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_NULL_MOBORUNAME_PASS));

			} else if (username.equalsIgnoreCase(" ") || username.equalsIgnoreCase("")) {

				return ResponseEntity
						.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_NULL_MOBILE_OR_UNAME));

			} else if (password.equalsIgnoreCase(" ") || password.equalsIgnoreCase("")) {
				return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_NULL_PASSWORD));

			}

			else {
//				String decryptedPassword = CryptoHelper.decrypt(password);
				System.out.println("Authentication..........");
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username, password));

				SecurityContextHolder.getContext().setAuthentication(authentication);

				String jwt = jwtProvider.generateJwtToken(authentication);

				// Get User all Details
				UserProfileModel userProfileModel = getwebUserDetails(username);

				userService.addWebUserToken(username, jwt);

				UserDetails userDetails = (UserDetails) authentication.getPrincipal();

				// Send Response with token and user_name
				JSONArray arr = new JSONArray();

				JSONObject jwtResponse = new JSONObject(
						new JwtResponse("Bearer " + jwt, userDetails.getAuthorities(), username));
				jwtResponse.put("user_id", userProfileModel.getUser_id());
				jwtResponse.put("name", userProfileModel.getName());
				jwtResponse.put("user_name", userProfileModel.getUser_name());
				jwtResponse.put("email_id", userProfileModel.getEmail_id());
				jwtResponse.put("role_id", userProfileModel.getRole_id());
				jwtResponse.put("role_name", userProfileModel.getRole_name());
				jwtResponse.put("modules", userProfileModel.getModules());
				arr.put(jwtResponse);

				JSONObject responseObj = new JSONObject();
				responseObj.put("responseCode", 200);
				responseObj.put("data", arr);
				responseObj.put("responseMessage", "Login Successful.");

				return ResponseEntity.ok(responseObj.toString());
			}

		} catch (Exception e) {
			System.out.println("ERROR while login ::: " + e);
			return ResponseEntity
					.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_FOR_USERNAME_OR_PASSWORD_INCORRECT));
		}

	}

	/**
	 * API For User register
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/register_web_user", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> registerData(@RequestBody UserModel json) {

		logger.info("---------------------- registeruser API called -----------------------");

		try {

			JSONObject jsonObject = new JSONObject(json);
//			String password = jsonObject.get("password").toString();
//			String contactNo = jsonObject.get("contact_no").toString();
			String password = json.getPassword();
			String contactNo = json.getContact_no();
			String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
			// String CONTACT_PATTERN="^\\\\d{10}$";

			Pattern pattern = Pattern.compile("^\\d{10}$");
			Matcher matcher = pattern.matcher(contactNo);

			if (!password.matches(PASSWORD_PATTERN)) {
				return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_PASSWORD_IS_INCORRECT));
			} else if (!matcher.matches()) {
				return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_CONTACT_IS_INCORRECT));
			} else {
				// password = jsonObject.get("password").toString();
				// jsonObject.put("password", encoder.encode(password));
				json.setPassword(encoder.encode(password));

				JSONObject ob = new JSONObject(json);
				System.out.println(ob.toString());

				String result = userService.registerNewUser(ob.toString());

				return ResponseEntity.ok(result);

			}

		} catch (Exception e) {
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_NO_JSON_400));

		}

	}

	/**
	 * this api will used to get all roles.
	 * 
	 * @param isForVendor
	 * @param httpServletRequest
	 * @return
	 */
	@ApiIgnore
	@ApiOperation(value = "Getting details by user id", authorizations = { @Authorization(value = "Bearer") })
	@RequestMapping(value = "/getUserDetailsById/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public String getUserDetailsById(@PathVariable(value = "user_id", required = true) Integer userId,
			HttpServletRequest httpServletRequest) {

		logger.info("----------------- getUserDetailsById  API Called---------------");
		String data = null;
		try {
			data = userService.getUserDetailsById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	/**
	 * this method used to get all user profile details based on username.
	 * 
	 * @param username
	 * @return
	 * @throws JSONException
	 */
	private UserProfileModel getUserProfileDetails(String username) throws JSONException {
		UserProfileModel userProfileModel = null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("contact_no", username);
		String userObject = userService.getUserProfile(jsonObject.toString());

		JSONObject responseJsonObject = new JSONObject(userObject);

		if (responseJsonObject != null && responseJsonObject.has("data") && responseJsonObject.has("responseCode")) {
			if (String.valueOf(responseJsonObject.get("responseCode")).equals("200")) {
				JSONArray jsonArray = new JSONArray(responseJsonObject.get("data").toString());
				userProfileModel = new Gson().fromJson(jsonArray.get(0).toString(), UserProfileModel.class);
			} else if (String.valueOf(responseJsonObject.get("responseCode")).equals("203")) {
				userProfileModel = new UserProfileModel();
				userProfileModel.setLogin_error(userObject);
			}

		}
		return userProfileModel;
	}

	/**
	 * this method used to get all user details based on username through web.
	 * 
	 * @param username
	 * @return
	 * @throws JSONException
	 */
	private UserProfileModel getwebUserDetails(String username) throws JSONException {
		UserProfileModel userProfileModel = null;
		// JSONObject jsonObject = new JSONObject();
		/// jsonObject.put("contact_no", username);
		String userObject = userService.getWebUserProfile(username);
		System.out.println("user web details " + userObject.toString());
		JSONObject responseJsonObject = new JSONObject(userObject);

		if (responseJsonObject != null && responseJsonObject.has("data")) {
			JSONArray jsonArray = new JSONArray(responseJsonObject.get("data").toString());
			userProfileModel = new Gson().fromJson(jsonArray.get(0).toString(), UserProfileModel.class);
		}
		return userProfileModel;
	}

	@ApiIgnore
	@ApiOperation(value = "User Profile API which provides user details for the logged in user", authorizations = {
			@Authorization(value = "Bearer") })
	@RequestMapping(value = "/getUserProfile", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getUserProfile(@RequestBody String json, HttpServletRequest request) {

		try {
			String requestJson = commonService.getRequestJson(request, json);
			commonService.saveApiLog(requestJson);

			String result = userService.getUserProfile(json);
			JSONObject obj = new JSONObject(result);

			JSONObject resultObj = new JSONObject();
			if (obj.get("data").toString().equalsIgnoreCase("[]")) {
				// List<Object> l=new ArrayList<Object>();
				int[] arr = {};
				resultObj.put("responseCode", 200);
				resultObj.put("responseMessage", obj.getString("responseMessage"));
				resultObj.put("data", arr);
			} else {
				String hostUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				JSONArray data = (JSONArray) obj.get("data");

				for (int i = 0; i < data.length(); i++) {
					JSONObject obj1 = data.getJSONObject(i);
					Object o = obj1.get("image_path");
					if (o.equals(null)) {

					} else {
						String image_path = obj1.getString("image_path");

						if (!image_path.equalsIgnoreCase("")) {
							// data.getJSONObject(i).put("signature_of_respondent",
							// request.getRequestURL().toString()+"/"+signature_of_respondent);
							data.getJSONObject(i).put("image_path", hostUrl + "/getUserSignatureImage/" + image_path);
						}
					}

				}

				// JSONObject resultObj=new JSONObject();
				resultObj.put("responseCode", 200);
				resultObj.put("responseMessage", "Ok");
				resultObj.put("data", data);
			}

			String responseJson = commonService.getResponseJson(requestJson, resultObj.toString());
			commonService.saveApiLog(responseJson);
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@ApiOperation(value = "User Logout API ", authorizations = { @Authorization(value = "Bearer") })
	@RequestMapping(value = "/userLogout", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> logout(@RequestBody String json, HttpServletRequest request) {
		try {
			String requestJson = commonService.getRequestJson(request, json);
			commonService.saveApiLog(requestJson);

			String result = userService.removeUserToken(request.getHeader("Authorization").replace("Bearer ", ""));

			String responseJson = commonService.getResponseJson(requestJson, result);
			commonService.saveApiLog(responseJson);
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.USER_LOGOUT_SUCCESSFUL));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}

	}

	@ApiIgnore
	@RequestMapping(value = "/storeTemporaryPassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseModel forgotPassword(@RequestBody String json, HttpServletRequest request) {

		ResponseModel rmodel = null;
		try {

			String requestJson = commonService.getRequestJson(request, json);
			commonService.saveApiLog(requestJson);

			String userData = userService.checkUserNameIsExist(json);
			ResponseModel responseModel = gson.fromJson(userData, ResponseModel.class);

			if (responseModel.getResponseCode() == 200) {// IF USER IS EXIST AND ACTIVE
				String randomPassword = RandomString.getAlphaNumericString(8); // GENERATE RANDOM PASSWORD
				if (randomPassword != null && randomPassword != "") {
					JSONObject userobj = new JSONObject(json);
					String contact_no = userobj.get("contact_no").toString();
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("contact_no", contact_no);
					jsonobj.put("temporary_password", randomPassword);
					String user_id = userService.storeTempararyPassword(jsonobj.toString());
					ResponseModel tempPasswordModel = gson.fromJson(user_id, ResponseModel.class);

					if (tempPasswordModel.getResponseCode() == 200) {// IF STORE TEMP PASSWORD IN DATABASE
						List<String> strings = new ArrayList<String>();
						strings.add("<p>Hello, Your Temporary password for username " + contact_no + " is  <b>"
								+ randomPassword + "</b> .</p>");
						try {
							// System.out.println("Email ID : " +responseModel.getData());
							mailUtils.sendMail(
									mailUtils.setEmailTemplate("Forgot Password", responseModel.getData(), strings));
							tempPasswordModel.setResponseMessage(
									"Temporary Password is sent to your registered Email Id. If you do not receive any email then make sure that you have entered correct Email Id and try again !");
							rmodel = tempPasswordModel;
						} catch (Exception e) {
							responseModel.setResponseCode(204);
							responseModel.setResponseMessage("Failed to send email");
							responseModel.setData(null);
							rmodel = responseModel;
							return rmodel;
							// e.printStackTrace();
						}
					} else {
						responseModel.setResponseCode(204);
						responseModel.setResponseMessage("Failed to store temporary pasword");
						responseModel.setData(null);
						rmodel = responseModel;
						return rmodel;
					}
				} else {
					responseModel.setResponseCode(204);
					responseModel.setResponseMessage("Random Password not generated");
					responseModel.setData(null);
					rmodel = responseModel;
					return rmodel;
				}
			} else {// IF USER IS NOT EXIST AND ACTIVE
				rmodel = responseModel;
				return rmodel;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rmodel;
	}

	/**
	 * this method used to Update user password for mobile application
	 * 
	 * @param username
	 * @return
	 * @throws JSONException
	 */
	@ApiIgnore
	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseModel updateUserPassword(@RequestBody String json, HttpServletRequest request) {

		ResponseModel rmodel = null;
		try {

//				String requestJson = commonService.getRequestJson(request,json);
//				commonService.saveApiLog(requestJson);

			JSONObject userobj = new JSONObject(json);

			String user_id = userobj.get("user_id").toString();
			String temp_pass = userobj.get("old_password").toString();
			String new_password = userobj.get("new_password").toString();
			String confirm_new_password = userobj.get("confirm_new_password").toString();

			// CHECK USER ID IS NOT NULL
			if (user_id == null || user_id == "" || user_id.equalsIgnoreCase("")) {
				rmodel = new ResponseModel();
				rmodel.setResponseCode(204);
				rmodel.setResponseMessage("User id should not be blank");
				rmodel.setData(null);
				return rmodel;
			}

			// CHECK TEMPORARY PASSWORD IS NOT NULL
			if (temp_pass == null || temp_pass == "" || temp_pass.equalsIgnoreCase("")) {
				rmodel = new ResponseModel();
				rmodel.setResponseCode(204);
				rmodel.setResponseMessage("Temporary password should not be blank");
				rmodel.setData(null);
				return rmodel;
			}

			// CHECK CONFIRM PASSWORD AND CHANGE PASSWORD SAME OR NOT
			if (!new_password.equalsIgnoreCase(confirm_new_password)) {
				rmodel = new ResponseModel();
				rmodel.setResponseCode(204);
				rmodel.setResponseMessage("Password and confirm password does not matched");
				rmodel.setData(null);
				return rmodel;
			}

			String encoded_password = encoder.encode(new_password);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("user_id", user_id);
			jsonobj.put("old_password", temp_pass);
			jsonobj.put("new_password", encoded_password);

			String response = userService.updateUserPassword(jsonobj.toString());
			ResponseModel responseModel = gson.fromJson(response, ResponseModel.class);
			rmodel = responseModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rmodel;
	}

	@RequestMapping(value = "/crud_user_management", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> crud_usermanagement(@RequestBody String json) {
		String resultObj = null;
		try {
			JSONObject obj = new JSONObject(json);
			String flag = obj.getString("flag");
			if(flag.equals("create")) {
				String password = obj.getString("password");
				obj.put("password", encoder.encode(password));
				resultObj = userService.crud_usermanagement(obj.toString());
			}else {
				resultObj = userService.crud_usermanagement(obj.toString());
			}
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@RequestMapping(value = "/get_all_role", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> get_all_role(@RequestBody String json) {
		String resultObj = null;
		try {
			resultObj = userService.get_all_role(json);
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@RequestMapping(value = "/add_update_role", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> add_update_role(@RequestBody String json) {
		String resultObj = null;
		try {
			resultObj = userService.add_update_role(json);
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@RequestMapping(value = "/delete_role", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> delete_role(@RequestBody String json) {
		String resultObj = null;
		try {
			resultObj = userService.delete_role(json);
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@ApiOperation(value = "Getting details by user id", authorizations = { @Authorization(value = "Bearer") })
	@RequestMapping(value = "/getRollDetailsById/{roll_id}", method = RequestMethod.GET, produces = "application/json")
	public String getRollDetailsById(@PathVariable(value = "roll_id", required = true) Integer rollId,
			HttpServletRequest httpServletRequest) {

		logger.info("----------------- getRollDetailsById  API Called---------------");
		String data = null;
		try {
			data = userService.getRollDetailsById(rollId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/get_all_surveyor", method = RequestMethod.GET, produces = "application/json")
	public String getAllSurveyor(HttpServletRequest httpServletRequest) {

		logger.info("----------------- getAllSurveyor  API Called---------------");
		String data = null;
		try {
			data = userService.getAllSurveyor();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/get_user_details_by_email", method = RequestMethod.POST, produces = "application/json")
	public String getUserDetailsByMail(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- getUserDetailsByMail  API Called---------------");
		String data = null;
		try {
			data = userService.getUserDetailsByMail(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/update_role_status", method = RequestMethod.POST, produces = "application/json")
	public String updateRoleStatus(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- updateRoleStatus  API Called---------------");
		String data = null;
		try {
			data = userService.updateRoleStatus(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/update_user_status", method = RequestMethod.POST, produces = "application/json")
	public String updateUserStatus(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- updateUserStatus  API Called---------------");
		String data = null;
		try {
			data = userService.updateUserStatus(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/reset_pasword", method = RequestMethod.POST, produces = "application/json")
	public String resetPassword(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- resetPassword  API Called---------------");
		String data = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			String encodedPassword = encoder.encode(jsonObject.getString("new_password").toString());
			jsonObject.put("encoded_new_password", encodedPassword);
			jsonObject.remove("new_password");
			data = userService.resetPassword(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/verify_otp", method = RequestMethod.POST, produces = "application/json")
	public String verifyOtp(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- verifyOtp  API Called---------------");
		String data = null;
		try {
			data = userService.verifyOtp(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/get_user_id_by_mail", method = RequestMethod.POST, produces = "application/json")
	public String getUserIdByMail(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- get_User_Id_By_Mail  API Called---------------");
		String data = null;
		try {
			data = userService.get_User_Id_By_Mail(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@ApiIgnore
	@RequestMapping(value = "/send_OTP", method = RequestMethod.POST, produces = "application/json")
	public String sendOtp(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- sendOTP  API Called---------------");
		String data = null;
		try {

			Random random = new Random();
			int otp = random.nextInt(9999);
			System.out.println(otp);

			JSONObject jsonObject = new JSONObject(json);
			jsonObject.put("otp", otp);
			String emailAddress = jsonObject.get("email").toString();
			System.out.println(emailAddress);

			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", "smtp.office365.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ss.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.user", "amnexsmtp@outlook.com");
			properties.put("mail.smtp.password", "smtpAmnex");

			// Session session = Session.getDefaultInstance(properties , null);

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("amnexsmtp@outlook.com", "smtpAmnex");
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amnexsmtp@outlook.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			message.setSubject("Digital Twin OTP");
			message.setText("Hello, /n" + "Below is your OTP for Reset Password" + otp);

			Transport.send(message);

			data = userService.send_OTP(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
	
	
	/**
	 * API For Surveyor register
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/crud_surveyor_management", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> registerSurveyor(@RequestBody String json) {
		logger.info("---------------------- register surveyor API called -----------------------");
		String resultObj;
		
		try {
			JSONObject obj = new JSONObject(json);
			String flag = obj.getString("flag");
			if(flag.equals("create")) {
				String password = obj.getString("password");
				obj.put("password", encoder.encode(password));
				resultObj = userService.registerSurveyor(obj.toString());
			}else {
				resultObj = userService.registerSurveyor(obj.toString());
			}
			return ResponseEntity.ok(resultObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	
	@ApiIgnore
	@RequestMapping(value = "/get_user_profile_by_userId", method = RequestMethod.POST, produces = "application/json")
	public String getUserProfileByUserId(@RequestBody String json, HttpServletRequest httpServletRequest) {

		logger.info("----------------- getUserProfileByUserId  API Called---------------");
		String data = null;
		try {
			data = userService.getUserProfileByUserId(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
	

}
