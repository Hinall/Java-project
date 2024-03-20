package com.digitalgis.dao;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public interface CommonDao {

	String getRequestId();

	String saveApiLog(String json);

	String getRequestJson(HttpServletRequest request, String json) throws JSONException;

	String getResponseJson(String json, String response) throws JSONException;

	String getVisitorCounter() throws JSONException;

	String updateVisitorCounter(String json) throws JSONException;

	String zoomToBBox(String json) throws JSONException;

	String insertVisitorCounter(String json) throws JSONException;

	String addFeedbackData(String json) throws JSONException;

	String getListOfFeedback() throws JSONException;

	String getCaptcha(String json) throws JSONException;

	String verify_captcha(String json) throws JSONException;

	String getBookmarkByUserId(String json) throws JSONException;

	String add_bookmark(String json) throws JSONException;

	String getInformationofLayer(String json) throws JSONException;

}
