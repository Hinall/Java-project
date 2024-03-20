package com.digitalgis.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.CommonDao;
import com.digitalgis.service.CommonService;

@Component
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDao commonDao;

	@Override
	public String getRequestId() {
		return commonDao.getRequestId();
	}

	@Override
	public String saveApiLog(String json) {
		String resultJson = commonDao.saveApiLog(json);
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
	public String getRequestJson(HttpServletRequest request, String json) throws JSONException {
		return commonDao.getRequestJson(request, json);
	}

	@Override
	public String getResponseJson(String json, String response) throws JSONException {
		return commonDao.getResponseJson(json, response);
	}

	@Override
	public String getVisitorCounter() throws JSONException {
		return commonDao.getVisitorCounter();
	}

	@Override
	public String updateVisitorCounter(String json) throws JSONException {
		return commonDao.updateVisitorCounter(json);
		
	}

	@Override
	public String zoomToBBox(String json) throws JSONException {
		return commonDao.zoomToBBox(json);
	}

	@Override
	public String insertVisitorCounter(String json) {
		return commonDao.insertVisitorCounter(json);
	}

	@Override
	public String addFeedbackData(String json) throws JSONException {
		return commonDao.addFeedbackData(json);
	}

	@Override
	public String getListOfFeedback() throws JSONException {
		return commonDao.getListOfFeedback();
	}

	@Override
	public String getCaptcha(String json) throws JSONException {
		return commonDao.getCaptcha(json);
	}

	@Override
	public String verify_captcha(String json) throws JSONException {
		return commonDao.verify_captcha(json);
	}

	@Override
	public String getBookmarkByUserId(String json) throws JSONException {
		return commonDao.getBookmarkByUserId(json);
	}

	@Override
	public String add_bookmark(String json) throws JSONException {
		return commonDao.add_bookmark(json);
	}

	@Override
	public String getInformationofLayer(String json) throws JSONException {
		return commonDao.getInformationofLayer(json);
	}

}
