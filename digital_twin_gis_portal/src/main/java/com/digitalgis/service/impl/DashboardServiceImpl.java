package com.digitalgis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.DashboardDao;
import com.digitalgis.service.DashboardService;

@Component
public class DashboardServiceImpl implements DashboardService{
	@Autowired
	DashboardDao dao;

	@Override
	public String getDrawFeatureData(String json) {
		String resultJson = dao.getDrawFeatureData(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getLulcCountDetails(String json) {
		String resultJson = dao.getLulcCountDetails(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getChangeAnalysisData(String json) {
		String resultJson = dao.getChangeAnalysisData(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getRetmsChartData(String json) {
		String resultJson = dao.getRetmsChartData(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public String getRetmsDashboardGridData(String json) {
		String resultJson = dao.getRetmsDashboardGridData(json);
		if (resultJson != null && !resultJson.isEmpty()) {
			try {
				return resultJson;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	
	
}
