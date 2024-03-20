package com.digitalgis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.UserDao;
import com.digitalgis.model.User;
import com.digitalgis.model.UserModel;
import com.digitalgis.utils.LoggerUtil;
import com.digitalgis.utils.SPUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@SuppressWarnings("deprecation")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	ObjectMapper ObjectMapper;

	@Autowired
	PasswordEncoder encoder;

	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public User checkUserLoginDeatils(String userName) {
		User user = null;
		String result = "";
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_CHECK_USER_DETAILS_FOR_LOGIN,
					new Object[] { userName }, String.class);
			user = ObjectMapper.readValue(result, User.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  checkwebUserLoginDeatils()");
			e.printStackTrace();
		}
		return user;
	}

//	@Override
//	public Integer addUserToken(String username, String token) {
//		try {
//			return jdbcTemplate.queryForObject(SPUtility.FN_ADD_USER_TOKEN, new Object[] { username, token },
//					Integer.class);
//		} catch (Exception e) {
//			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  addUserToken()");
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	@Override
	public String getUserProfile(String json) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_GET_USER_PROFILE, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  getUserProfile()");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public String updateUserSettings(String json) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_update_user_settings(?)", new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  updateUserSettings()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String verifyUserToken(String token) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_web_verify_user_token(?)", new Object[] { token },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  verifyUserToken()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String removeUserToken(String token) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_remove_user_token(?)", new Object[] { token },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  removeUserToken()");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public String registerNewUser(String json) {
		String result = "";
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_CRUD_USER_MANAGEMENT, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  removeUserToken()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getWebUserProfile(String json) {

		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_WEB_GET_USER_PROFILE, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  getWebUserProfile()");
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public String getUserDetailsById(Integer userId) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_WEB_GET_USER_DETAILS_BY_USER_ID, new Object[] { userId },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  getUserDetailsById()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String checkUserNameIsExist(String json) {
		

		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_WEB_GET_USER_EMAILID, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  checkUserNameIsExist()");
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public String storeTempararyPassword(String json) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_WEB_UPDATE_USER_TEMPORARY_PASSWORD, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  storeTempararyPassword()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateUserPassword(String json) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList(SPUtility.FN_UPDATE_USER_PASSWORD, new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  updateWebForgotUserPassword()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer addWebUserToken(String username, String token) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_ADD_USER_TOKEN, new Object[] { username, token },
					Integer.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  addWebUserToken()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String add_update_role(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_CRUD_ROLE_MANAGEMENT, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  add_update_role()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String delete_role(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_DELETE_ROLE, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  delete_role()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String get_all_role(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_ALL_ROLE, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  get_all_role()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String crud_usermanagement(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_CRUD_USER_MANAGEMENT, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  get_all_role()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getRollDetailsById(Integer rollId) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_ROLL_DETAILS_BY_ROLLID, new Object[] { rollId },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in UserDaoImpl ::  get_all_role()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAllSurveyor() {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_ALL_SURVEYOR, new Object[] { },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getAllSurveyor ::  getAllSurveyor()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserDetailsByMail(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_USER_DETAILS_BY_EMAIL, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getUserDetailsByMail ::  getUserDetailsByMail()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateRoleStatus(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_UPDATE_ROLE_STATUS, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in updateRoleStatus ::  updateRoleStatus()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateUserStatus(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_UPDATE_USER_STATUS, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in updateRoleStatus ::  updateRoleStatus()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String resetPassword(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_RESET_PASSWORD, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in updateRoleStatus ::  updateRoleStatus()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String verifyOtp(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_VERIFY_OTP, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in verifyOtp ::  verifyOtp()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String get_User_Id_By_Mail(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_GET_USER_ID_BY_MAIL, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in get_User_Id_By_Mail ::  get_User_Id_By_Mail()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String send_OTP(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_INSERT_OTP, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in send_OTP ::  send_OTP()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String registerSurveyor(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_REGISTER_SURVEYOR, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in registerSurveyor ::  registerSurveyor()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserProfileByUserId(String json) {
		try {
			return jdbcTemplate.queryForObject(SPUtility.FN_WEB_USER_PROFILE_BY_USER_ID, new Object[] { json },
					String.class);
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getUserProfileByUserId ::  getUserProfileByUserId()");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
