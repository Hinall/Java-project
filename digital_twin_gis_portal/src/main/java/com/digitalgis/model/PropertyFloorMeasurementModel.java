package com.digitalgis.model;

public class PropertyFloorMeasurementModel {

	private Integer survey_floor;
	private Integer type_of_construction;
	private String total_plot_area;
	private String individual_property_area;
	private String total_built_up_area;

	public Integer getSurvey_floor() {
		return survey_floor;
	}

	public void setSurvey_floor(Integer survey_floor) {
		this.survey_floor = survey_floor;
	}

	public Integer getType_of_construction() {
		return type_of_construction;
	}

	public void setType_of_construction(Integer type_of_construction) {
		this.type_of_construction = type_of_construction;
	}

	public String getTotal_plot_area() {
		return total_plot_area;
	}

	public void setTotal_plot_area(String total_plot_area) {
		this.total_plot_area = total_plot_area;
	}

	public String getIndividual_property_area() {
		return individual_property_area;
	}

	public void setIndividual_property_area(String individual_property_area) {
		this.individual_property_area = individual_property_area;
	}

	public String getTotal_built_up_area() {
		return total_built_up_area;
	}

	public void setTotal_built_up_area(String total_built_up_area) {
		this.total_built_up_area = total_built_up_area;
	}

}
