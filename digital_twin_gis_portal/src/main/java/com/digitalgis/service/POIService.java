package com.digitalgis.service;

import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public interface POIService {
	
	String getPOIAttributes(String json) throws JSONException;
	
	String savePOIData(String json) throws JSONException;
	
	String getPOIMasterData() throws JSONException;
	
	String downloadPOIData(String json) throws JSONException;
}
