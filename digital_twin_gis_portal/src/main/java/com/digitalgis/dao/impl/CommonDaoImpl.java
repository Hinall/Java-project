package com.digitalgis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.CommonDao;
import com.digitalgis.utils.LoggerUtil;
import com.digitalgis.utils.SPUtility;
import com.digitalgis.utils.Utility;

@Component
@SuppressWarnings("deprecation")
public class CommonDaoImpl extends JdbcDaoSupport implements CommonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CommonDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public String getRequestId() {
		String requestId = Utility.generateRandomString(6);
		return requestId;
	}

	@Override
	public String saveApiLog(String json) {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_save_api_log(?)", new Object[] { json }, String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in CommonDaoImpl ::  saveApiLog()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getRequestJson(HttpServletRequest request, String json) throws JSONException {
		if (request == null) {
			return null;
		}

		JSONObject obj = new JSONObject();
		obj.put("request_id", getRequestId());
		obj.put("request_url", request.getRequestURI());
		obj.put("request_body", json);
		String finalObj = obj.toString();

		return finalObj;
	}

	@Override
	public String getResponseJson(String json, String response) throws JSONException {
		if (json == null) {
			return null;
		}
		JSONObject obj = new JSONObject(json);
		obj.put("response_body", response);
		String finalObj = obj.toString();
		return finalObj;
	}

	@Override
	public String getVisitorCounter() throws JSONException {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_get_visitor_count()", String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in CommonDaoImpl ::  getVisitorCounter()" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateVisitorCounter(String json) throws JSONException {
		List<String> result = new ArrayList<>();
		try {
			result = jdbcTemplate.queryForList("SELECT public.fn_update_visitor_counter(?)", new Object[] { json },
					String.class);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in CommonDaoImpl ::  updateVisitorCounter()" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String zoomToBBox(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_ZOOM_TO_BBOX, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in zoomToBBox ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String insertVisitorCounter(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_INSERT_AUDIT_LOG, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in insertVisitorCounter ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addFeedbackData(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_ADD_FEEDBACK, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in addFeedbackData ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getListOfFeedback() throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_LIST_FEEDBACK, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getListOfFeedback ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getCaptcha(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_VERIFY_CAPCHA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getCaptcha ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String verify_captcha(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_VERIFY_CAPCHA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in verify_captcha ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getBookmarkByUserId(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_BOOKMARK_BY_ID, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getBookmarkByUserId ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String add_bookmark(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_ADD_BOOKMARK, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in add_bookmark ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getInformationofLayer(String json) throws JSONException {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_INFORMATION_OF_LAYERS, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in add_bookmark ");
			e.printStackTrace();
			return null;
		}
	}


}
