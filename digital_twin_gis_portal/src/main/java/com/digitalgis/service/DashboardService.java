package com.digitalgis.service;

import org.springframework.stereotype.Service;

@Service
public interface DashboardService {

	String getDrawFeatureData(String json);

	String getLulcCountDetails(String json);

	String getChangeAnalysisData(String json);

	String getRetmsChartData(String json);

	String getRetmsDashboardGridData(String json);
	
}
