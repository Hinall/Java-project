package com.digitalgis.service;

import org.springframework.stereotype.Service;

@Service
public interface PropertySurveyService {

	String getAllLayers();

	String getVerifyFeatureData(String json);

	String drawFeatureData(String json);

	String getDrawFeatureData(String json);

	String getWardData();

	String getSurveyDetails(String json);

	String getSurveyDetailsById(String json);

	String getWebParentLayer();

	String getWebAllLayer();

	String getPrabhagByWard(String json);

	String getSqkmByWard(String json);

	String getWebLayerAndImage(String json);

	String getDynamicChartData(String json);

	String getMasterDataByKey(String json);

	String getSwipeLayerData(String json);

	String getWebAttributeCount(String json);

	String getWebGetChangeAnalysisImage(String json);

	String getChangeAnalysisLayerData(String json);

	String getBarChartData(String json);

	String deleteDrawData(String json);

	String getLayerDataByLayername(String json);

	String getChangeAnalysisReport(String json);

	String insert_re_integration_data(String json);

	String getREIntegrationData(String reqJson);

	String getAllStateName();

	String getwardIdByStateName(String stateName);

	String gridDataByWardId(String wardId);

	String getUrl(String Json);
	
}
