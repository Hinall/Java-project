package com.digitalgis.utils;

public class SPUtility {
	
	// Mobile SP
	public final static String FN_WEB_CHECK_USER_DETAILS_FOR_LOGIN = "SELECT public.fn_web_check_user_login_details(?)";
	
	public final static String FN_GET_USER_PROFILE = "SELECT public.fn_get_user_details(?)";
	
	//public final static String FN_ADD_USER_TOKEN = "SELECT public.fn_add_user_token(?,?)";
	
	public final static String FN_GET_ALL_LAYERS = "SELECT fn_get_all_layers()";
	
	public final static String FN_UPDATE_USER_PASSWORD = "SELECT fn_update_user_password(?)";
	
	public final static String FN_GET_VERIFY_FEATURE_DATA = "SELECT fn_get_verify_feature_data(?)";
	
	public final static String FN_DRAW_FEATURE_DATA = "SELECT fn_add_draw_feature_data(?)";
	
	
	
	
	// WEB SP
	public final static String FN_WEB_GET_USER_PROFILE = "SELECT public.fn_user_web_login_with_user_name(?)";
	
	public final static String FN_WEB_ADD_USER_TOKEN = "SELECT public.fn_add_web_user_token(?,?)";
	
	public final static String FN_WEB_CRUD_USER_MANAGEMENT = "SELECT public.fn_web_crud_user_management_details(?)";
	
	public final static String FN_WEB_GET_USER_DETAILS_BY_USER_ID = "SELECT public.fn_web_get_user_details_by_userid(?)";
	
	public final static String FN_WEB_GET_USER_EMAILID = "SELECT public.fn_get_web_user_emailid(?)";
	
	public final static String FN_WEB_UPDATE_USER_TEMPORARY_PASSWORD = "SELECT public.fn_update_web_user_temporary_password(?)";
	
	public final static String FN_WEB_CRUD_ROLE_MANAGEMENT = "SELECT public.fn_web_add_or_update_role(?)";
	
	public final static String FN_WEB_GET_ALL_ROLE = "SELECT public.fn_web_get_all_role(?)";
	
	public final static String FN_WEB_DELETE_ROLE = "SELECT public.fn_web_delete_role(?)";
	
	public final static String FN_GET_WARD_DATA = "SELECT fn_get_ward_data()";
	
	public final static String FN_WEB_GET_SURVEY_DETAILS = "SELECT fn_web_get_survey_details(?)";
	
	public final static String FN_WEB_GET_SURVEY_DETAILS_BY_ID = "SELECT fn_web_get_survey_details_by_id(?)";
	
	public final static String FN_WEB_GET_PARENT_LAYER_LIST = "SELECT fn_web_get_all_parent_layer_list()";
	
	public final static String FN_WEB_GET_LAYER_LIST = "SELECT fn_web_get_layer_data()";
	
	public final static String FN_WEB_GET_ROLL_DETAILS_BY_ROLLID = "SELECT public.fn_web_get_role_by_role_id(?)";
	
	public final static String FN_WEB_GET_ALL_SURVEYOR = "SELECT public.fn_web_get_all_surveyor()";
	
	public final static String FN_WEB_USER_DETAILS_BY_EMAIL = "SELECT public.fn_get_user_id_by_mail()";
	
	public final static String FN_WEB_GET_PRABHAG_BY_WARD = "SELECT public.fn_web_get_prabhag_by_ward(?)";
	
	public final static String FN_WEB_GET_SQKM_BY_WARD = "SELECT public.fn_web_get_sq_km_by_ward(?)";
	
	public final static String FN_WEB_GET_ALL_LAYER_IMAGE = "SELECT public.fn_web_get_all_layer_image(?)";
	
	public final static String FN_WEB_GET_DYNAMIC_CHART_DATA = "SELECT public.fn_web_get_dynamic_chart_data(?)";
	
	public final static String FN_WEB_GET_BAR_CHART_DATA = "SELECT public.fn_web_get_bar_chart_data(?)";
	
	public final static String FN_WEB_GET_MASTER_DATA_BY_KEY = "SELECT public.fn_web_get_master_data_by_key(?)";
	
	public final static String FN_WEB_UPDATE_ROLE_STATUS = "SELECT public.fn_role_status_update(?)";
	
	public final static String FN_WEB_UPDATE_USER_STATUS = "SELECT public.fn_user_status_update(?)";
	
	public final static String FN_WEB_RESET_PASSWORD = "SELECT public.fn_web_reset_password(?)";
	
	public final static String FN_WEB_VERIFY_OTP = "SELECT public.fn_verify_otp(?)";
	
	public static final String FN_GET_USER_ID_BY_MAIL = "SELECT public.fn_get_user_id_by_mail(?)";
	
	public static final String FN_INSERT_OTP = "SELECT public.fn_insert_otp(?)";
	
	public static final String FN_WEB_REGISTER_SURVEYOR = "SELECT public.fn_web_crud_surveyor_management(?)";
	
	public final static String FN_WEB_GET_LULC_COUNT_DETAILS = "SELECT public.fn_web_get_lulc_count_data(?)";
	
	public final static String FN_WEB_GET_SWIPE_LAYER_DATA = "SELECT public.fn_web_get_swipe_layer(?)";
	
	public final static String FN_WEB_GET_CHANGE_ANALYSIS_LAYER_DATA = "SELECT public.fn_web_get_change_analysis_layer(?)";
	
	public final static String FN_WEB_DELETE_DRAW_DATA = "SELECT public.fn_web_delete_draw_data(?)";
	
	public final static String FN_WEB_LAYER_DATA_BY_LAYERNAME = "SELECT public.fn_get_layer_data_by_layername(?)";
	
	public final static String FN_WEB_CHANGE_ANALYSIS_REPORT = "SELECT public.fn_get_change_analysis_report(?)";
	
	public final static String FN_WEB_INSERT_RE_INTEGRATION_DATA = "SELECT public.fn_web_insert_re_instegration_data(?)";
	
	public final static String FN_WEB_GET_RE_INTEGRATION_DATA = "SELECT public.fn_web_get_re_instegration_data(?)";
	
	//
	public final static String FN_GET_DRAW_FEATURE_DATA = "SELECT public.fn_get_draw_feature_data(?)";
	
	public final static String FN_WEB_ZOOM_TO_BBOX = "SELECT public.fn_web_zoom_to_bbox(?)";

	public static final String FN_INSERT_AUDIT_LOG = "SELECT public.fn_web_insert_audit_log(?)";

	public static final String FN_WEB_GET_ATTRIBUTE_COUNT = "SELECT public.fn_web_get_attribute_count(?)";

	public static final String FN_WEB_GET_CHANGE_ANALYSIS_DATA = "SELECT public.fn_web_get_change_analysis_data(?)";
	
	public static final String FN_WEB_ADD_FEEDBACK = "SELECT public.fn_web_feedback(?)";
	
	public static final String FN_WEB_GET_LIST_FEEDBACK = "SELECT public.fn_web_get_feedback_list()";
	
	public static final String FN_WEB_VERIFY_CAPCHA = "SELECT public.fn_web_verify_captcha(?)";
	
	public static final String FN_WEB_GET_CHANGE_ANALYSIS_IMAGE = "SELECT public.fn_web_get_change_analysis_image(?)";
	
	public static final String FN_WEB_GET_BOOKMARK_BY_ID = "SELECT public.fn_web_get_bookmark_by_id(?)";
	
	public static final String FN_WEB_ADD_BOOKMARK = "SELECT public.fn_web_add_bookmark(?)";
	
	public static final String FN_WEB_USER_PROFILE_BY_USER_ID = "SELECT public.fn_web_user_profile_by_user_id(?)";
	
	public static final String FN_WEB_GET_INFORMATION_OF_LAYERS = "SELECT public.fn_get_info(?)";
	
	public static final String FN_WEB_GET_RETMS_CHART_DATA = "SELECT public.fn_web_get_retms_chart_data(?)";
	
	public static final String FN_WEB_GET_RETMS_DASHBOARD_GRID_DATA = "SELECT public.fn_web_get_retms_dashboard_grid_data(?)";
	
	public static final String FN_GET_STATE_NAME= "SELECT public.fn_get_state_name()";
	
	public static final String FN_GET_WARD_ID= "SELECT public.fn_get_ward_id(?)";	
	
	public static final String FN_WEB_GET_RETMS_DASHBOARD_GRID_DATA_BY_WARD_ID= "SELECT public.fn_web_get_retms_dashboard_grid_data_by_wardId(?)";	

	public static final String FN_GET_URL= "SELECT public.fn_get_url(?,?)";	


}
