package com.digitalgis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.PropertySurveyDao;
import com.digitalgis.service.PropertySurveyService;

@Component
public class PropertySurveyServiceImpl implements PropertySurveyService {

	@Autowired
	private PropertySurveyDao propertySurveyDao;


	@Override
	public String getVerifyFeatureData(String json) {
		String resultJson = propertySurveyDao.getVerifyFeatureData(json);
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
	public String drawFeatureData(String json) {
		String resultJson = propertySurveyDao.drawFeatureData(json);
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
	public String getDrawFeatureData(String json) {
		String resultJson = propertySurveyDao.getDrawFeatureData(json);
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
	public String getWardData() {
		String resultJson = propertySurveyDao.getWardData();
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
	public String getSurveyDetails(String json) {
		String resultJson = propertySurveyDao.getSurveyDetails(json);
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
	public String getSurveyDetailsById(String json) {
		String resultJson = propertySurveyDao.getSurveyDetailsById(json);
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
	public String getWebParentLayer() {
		String resultJson = propertySurveyDao.getWebParentLayer();
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
	public String getWebAllLayer() {
		String resultJson = propertySurveyDao.getWebAllLayer();
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
	public String getPrabhagByWard(String json) {
		String resultJson = propertySurveyDao.getPrabhagByWard(json);
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
	public String getSqkmByWard(String json) {
		String resultJson = propertySurveyDao.getSqkmByWard(json);
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
	public String getWebLayerAndImage(String json) {
		String resultJson = propertySurveyDao.getWebLayerAndImage(json);
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
	public String getDynamicChartData(String json) {
		String resultJson = propertySurveyDao.getDynamicChartData(json);
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
	public String getMasterDataByKey(String json) {
		String resultJson = propertySurveyDao.getMasterDataByKey(json);
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
	public String getSwipeLayerData(String json) {
		String resultJson = propertySurveyDao.getSwipeLayerData(json);
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
	public String getWebAttributeCount(String json) {
		String resultJson = propertySurveyDao.getWebAttributeCount(json);
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
	public String getWebGetChangeAnalysisImage(String json) {
		String resultJson = propertySurveyDao.getWebGetChangeAnalysisImage(json);
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
	public String getChangeAnalysisLayerData(String json) {
		String resultJson = propertySurveyDao.getChangeAnalysisLayerData(json);
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
	public String getBarChartData(String json) {
		String resultJson = propertySurveyDao.getBarChartData(json);
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
	public String deleteDrawData(String json) {
		String resultJson = propertySurveyDao.deleteDrawData(json);
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
	public String getLayerDataByLayername(String json) {
		String resultJson = propertySurveyDao.getLayerDataByLayername(json);
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
	public String getChangeAnalysisReport(String json) {
		String resultJson = propertySurveyDao.getChangeAnalysisReport(json);
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
	public String insert_re_integration_data(String json) {
		String resultJson = propertySurveyDao.insert_re_integration_data(json);
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
	public String getREIntegrationData(String json) {
		String resultJson = propertySurveyDao.getREIntegrationData(json);
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
	public String getAllLayers() {
		String resultJson = propertySurveyDao.getAllLayers();
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
	public String getAllStateName() {
		String resultJson = propertySurveyDao.getAllStateName();
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
	public String getwardIdByStateName(String stateName) {
		String resultJson = propertySurveyDao.getWardByStateName(stateName);
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
	public String gridDataByWardId(String wardId) {
		String resultJson = propertySurveyDao.gridDataByWardId(wardId);
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
	public String getUrl(String Json) {
		String resultJson = propertySurveyDao.getUrl(Json);
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
