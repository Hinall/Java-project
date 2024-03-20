package com.digitalgis.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.digitalgis.dao.DashboardDao;
import com.digitalgis.utils.LoggerUtil;
import com.digitalgis.utils.SPUtility;

@Component
@SuppressWarnings("deprecation")
public class DashboardDaoImpl extends JdbcDaoSupport implements DashboardDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public DashboardDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public String getDrawFeatureData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_GET_DRAW_FEATURE_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getDrawFeatureData ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getLulcCountDetails(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_LULC_COUNT_DETAILS, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getLulcCountDetails ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getChangeAnalysisData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_CHANGE_ANALYSIS_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisData ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getRetmsChartData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_RETMS_CHART_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisData ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getRetmsDashboardGridData(String json) {
		String result;
		try {
			result = jdbcTemplate.queryForObject(SPUtility.FN_WEB_GET_RETMS_DASHBOARD_GRID_DATA, new Object[] { json },
					String.class);
			return result;
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "Error in getChangeAnalysisData ");
			e.printStackTrace();
			return null;
		}
	}

}
