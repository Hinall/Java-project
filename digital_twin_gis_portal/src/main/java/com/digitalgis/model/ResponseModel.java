package com.digitalgis.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String responseMessage;
	private Integer responseCode;
	private String data;

	public ResponseModel() {
	}

	public ResponseModel(String responseMessage, Integer responseCode) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	public ResponseModel(String responseMessage, Integer responseCode, String data) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.data = data;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
