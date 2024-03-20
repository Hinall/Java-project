package com.digitalgis.model;

import java.io.Serializable;

public class Response<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 499145002760860799L;
	private int statusCode;
	private String message;
	private boolean status;
	private T data;
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
