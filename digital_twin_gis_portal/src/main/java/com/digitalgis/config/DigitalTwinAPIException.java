package com.digitalgis.config;

/**
 * This is custom exception which contains error code & message. This will be
 * thrown by any class when custom error occurs.
 * 
 * @author jaydip.golviya
 * @since 09/04/2021
 */
public class DigitalTwinAPIException extends Exception {

	private static final long serialVersionUID = -3144332640812445838L;
	private int responseCode;
	private String responseMessage;

	public DigitalTwinAPIException(int code, String responseMessage) {
		this.responseCode = code;
		this.responseMessage = responseMessage;
	}

	public int getCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

}
