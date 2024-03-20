package com.digitalgis.dao.impl;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.PropertySurveyDao;
import com.digitalgis.utils.LoggerUtil;
import com.digitalgis.utils.SPUtility;

@Component
@SuppressWarnings("deprecation")
public class PropertySurveyDaoImpl extends JdbcDaoSupport implements PropertySurveyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PropertySurveyDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public String getAllLayers() {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_ALL_LAYERS, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getAllLayers()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getVerifyFeatureData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_VERIFY_FEATURE_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getVerifyFeatureData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String drawFeatureData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_DRAW_FEATURE_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  drawFeatureData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getDrawFeatureData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_DRAW_FEATURE_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getDrawFeatureData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWardData() {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_WARD_DATA, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getWardData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSurveyDetails(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_SURVEY_DETAILS, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getSurveyDetails()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSurveyDetailsById(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_SURVEY_DETAILS_BY_ID, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getSurveyDetailsById()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWebParentLayer() {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_PARENT_LAYER_LIST, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getWebParentLayer()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWebAllLayer() {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_LAYER_LIST, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getWebAllLayer()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getPrabhagByWard(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_PRABHAG_BY_WARD, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getPrabhagByWard()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSqkmByWard(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_SQKM_BY_WARD, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getPrabhagByWard()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWebLayerAndImage(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_ALL_LAYER_IMAGE, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getWebLayerAndImage ::  getWebLayerAndImage()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getDynamicChartData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_DYNAMIC_CHART_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getDynamicChartData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getMasterDataByKey(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_MASTER_DATA_BY_KEY, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl ::  getMasterDataByKey()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSwipeLayerData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_SWIPE_LAYER_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getSwipeLayerData ::  getSwipeLayerData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWebAttributeCount(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_ATTRIBUTE_COUNT, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getWebAttributeCount ::  getWebAttributeCount()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getWebGetChangeAnalysisImage(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_CHANGE_ANALYSIS_IMAGE, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getWebGetChangeAnalysisImage ::  getWebGetChangeAnalysisImage()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getChangeAnalysisLayerData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_CHANGE_ANALYSIS_LAYER_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisLayerData ::  getChangeAnalysisLayerData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getBarChartData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_BAR_CHART_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisLayerData ::  getChangeAnalysisLayerData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteDrawData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_DELETE_DRAW_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in deleteDrawData ::  deleteDrawData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getLayerDataByLayername(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_LAYER_DATA_BY_LAYERNAME, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in deleteDrawData ::  deleteDrawData()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getChangeAnalysisReport(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_CHANGE_ANALYSIS_REPORT, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisReport ::  getChangeAnalysisReport()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String insert_re_integration_data(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_INSERT_RE_INTEGRATION_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in insert_re_integration_data ::  insert_re_integration_data()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getREIntegrationData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_RE_INTEGRATION_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getREIntegrationData ::  getREIntegrationData(?)");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAllStateName() {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_STATE_NAME, new Object[] { },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl :: getAllStateName()");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getWardByStateName(String stateName) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_WARD_ID, new Object[] {stateName},
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl :: getWardByStateName()");
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String gridDataByWardId(String wardId) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_RETMS_DASHBOARD_GRID_DATA_BY_WARD_ID, new Object[] {wardId},
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl :: getWardByStateName()");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getUrl(String Json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_URL, new Object[] {Json},
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in PropertySurveyDaoImpl :: getUrl()");
			e.printStackTrace();
			return null;
		}
	}

}
