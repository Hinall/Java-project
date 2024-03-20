package com.digitalgis.config;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitalgis.utils.CustomMessages;
import com.google.gson.Gson;

@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * This method all custom generated exception.
	 * 
	 * @param ssclAPIException
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(DigitalTwinAPIException.class)
	@ResponseBody
	public ResponseEntity<String> handleProgramException(DigitalTwinAPIException digitalAPIException) throws IOException {
		switch (digitalAPIException.getCode()) {
		case 1:
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		default:
			break;
		}
		return ResponseEntity.ok(new Gson().toJson(digitalAPIException));
	}
}