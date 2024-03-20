package com.digitalgis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.UserDao;
import com.digitalgis.model.User;
import com.digitalgis.model.UserModel;
import com.digitalgis.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User checkUserLoginDeatils(String userName) {
		try {
			User resultJson = userDao.checkUserLoginDeatils(userName);
			return resultJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	@Override
//	public Integer addUserToken(String username, String token) {
//		try {
//			Integer result = userDao.addUserToken(username, token);
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	@Override
	public String getUserProfile(String json) {
		String resultJson = userDao.getUserProfile(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String verifyUserToken(String token) {
		String resultJson = userDao.verifyUserToken(token);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String removeUserToken(String token) {
		String resultJson = userDao.removeUserToken(token);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String registerNewUser(String json) {
		String resultJson = userDao.registerNewUser(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getWebUserProfile(String json) {
		String resultJson = userDao.getWebUserProfile(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getUserDetailsById(Integer userId) {
		String resultJson = userDao.getUserDetailsById(userId);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String checkUserNameIsExist(String json) {

		String resultJson = userDao.checkUserNameIsExist(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	
	}

	@Override
	public String storeTempararyPassword(String json) {
		String resultJson = userDao.storeTempararyPassword(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String updateUserPassword(String json) {
		String resultJson = userDao.updateUserPassword(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Integer addWebUserToken(String username, String token) {
		try {
			Integer result = userDao.addWebUserToken(username, token);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String add_update_role(String json) {
		String resultJson = userDao.add_update_role(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String delete_role(String json) {
		String resultJson = userDao.delete_role(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String get_all_role(String json) {
		String resultJson = userDao.get_all_role(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String crud_usermanagement(String json) {
		String resultJson = userDao.crud_usermanagement(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getRollDetailsById(Integer rollId) {
		String resultJson = userDao.getRollDetailsById(rollId);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAllSurveyor() {
		String resultJson = userDao.getAllSurveyor();
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getUserDetailsByMail(String json) {
		String resultJson = userDao.getUserDetailsByMail(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String updateRoleStatus(String json) {
		String resultJson = userDao.updateRoleStatus(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String updateUserStatus(String json) {
		String resultJson = userDao.updateUserStatus(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String resetPassword(String json) {
		String resultJson = userDao.resetPassword(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String verifyOtp(String json) {
		String resultJson = userDao.verifyOtp(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String get_User_Id_By_Mail(String json) {
		String resultJson = userDao.get_User_Id_By_Mail(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String send_OTP(String json) {
		String resultJson = userDao.send_OTP(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String registerSurveyor(String json) {
		String resultJson = userDao.registerSurveyor(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getUserProfileByUserId(String json) {
		String resultJson = userDao.getUserProfileByUserId(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
