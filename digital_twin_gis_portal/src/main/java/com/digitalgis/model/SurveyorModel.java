package com.digitalgis.model;

public class SurveyorModel {
	
	private int user_id;
	private String user_name;
	private String name;
	private String contact_no;
	private String email_id;
	private int role_id;
	private String password;
	private boolean status;
	private String flag;
	
	
	public SurveyorModel(int user_id, String user_name, String name, String contact_no, String email_id, int role_id,
			String password, boolean status, String flag) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.name = name;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.role_id = role_id;
		this.password = password;
		this.status = status;
		this.flag = flag;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SurveyorModel [user_id=" + user_id + ", user_name=" + user_name + ", name=" + name + ", contact_no="
				+ contact_no + ", email_id=" + email_id + ", role_id=" + role_id + ", password=" + password
				+ ", status=" + status + ", flag=" + flag + "]";
	}
	
}
