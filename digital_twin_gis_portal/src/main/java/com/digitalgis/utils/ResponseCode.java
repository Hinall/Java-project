package com.digitalgis.utils;

import java.io.Serializable;

/**
 * This is used to give response code & message to client request.
 * 
 * @author jaydip.golviya
 * @since 09/04/2021
 */
public enum ResponseCode implements Serializable {

	PROFILE_PIC_IMAGE_NOT_SUPPORTED(201, "Please upload valid image(jpg,png) file, Other file types are not allowed."),
	UNABLE_TO_FILE_UPLOAD(201, "Unable to upload image");

	private final int code;
	private final String message;

	ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * This methods is used to fetch Enum base on given id.
	 * 
	 * @param code
	 * @return ResponseCode enum
	 */
	public static ResponseCode fromId(int code) {
		for (ResponseCode responseCode : values()) {
			if (responseCode.code == code) {
				return responseCode;
			}
		}
		return null;
	}
}