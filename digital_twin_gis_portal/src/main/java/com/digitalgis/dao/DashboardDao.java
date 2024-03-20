package com.digitalgis.dao;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardDao {

	String getDrawFeatureData(String json);

	String getLulcCountDetails(String json);

	String getChangeAnalysisData(String json);

	String getRetmsChartData(String json);

	String getRetmsDashboardGridData(String json);
	
}
