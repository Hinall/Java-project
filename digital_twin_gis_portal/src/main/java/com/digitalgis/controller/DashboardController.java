package com.digitalgis.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalgis.service.DashboardService;
import com.digitalgis.utils.CustomMessages;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	DashboardService dashService;
	
	
	
	
	
	
	
	// WRITE A USED API ABOVE THIS COMMENTS
	
	
	
	
	
	
//	@RequestMapping(value = "/get_ward_data", method = RequestMethod.POST, produces = "application/json")
//	public String getWardData(@RequestBody String json) throws JSONException {
//
//		// logger.info("-----------------get ward data API Called---------------");
//		try {
//
//			String alldata = dashService.getAllWardData(json);
//			return alldata;
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			return CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_NO_JSON_400);
//
//		}
//	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_draw_feature_data", method = RequestMethod.POST)
	public ResponseEntity<?> getDrawFeatureData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = dashService.getDrawFeatureData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_lulc_count_details", method = RequestMethod.POST)
	public ResponseEntity<?> getLulcCountDetails(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = dashService.getLulcCountDetails(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_change_analysis_data", method = RequestMethod.POST)
	public ResponseEntity<?> getChangeAnalysisData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = dashService.getChangeAnalysisData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	
	@ApiIgnore
	@RequestMapping(value = "/get_retms_chart_data", method = RequestMethod.POST)
	public ResponseEntity<?> getRetmsChartData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = dashService.getRetmsChartData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_retms_dashboard_grid_data", method = RequestMethod.POST)
	public ResponseEntity<?> getRetmsDashboardGridData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = dashService.getRetmsDashboardGridData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
}
