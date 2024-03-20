package com.digitalgis.model;

/**
 * this model present user profile details as per database fields.
 * 
 * @author jaydip.golviya
 *
 */
public class UserProfileModel {

	private Long user_id;
	private String name;
	private String user_name;
	private String email_id;
	private Integer role_id;
	private String role_name;
	private String[] modules;
	private String login_error;

	public String[] getModules() {
		return modules;
	}

	public void setModules(String[] modules) {
		this.modules = modules;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}



	public String getLogin_error() {
		return login_error;
	}

	public void setLogin_error(String login_error) {
		this.login_error = login_error;
	}

	@Override
	public String toString() {
		return "UserProfileModel [user_id=" + user_id + ", name=" + name + ", user_name=" + user_name + ", email_id="
				+ email_id + ", role_id=" + role_id + ", role_name=" + role_name + "]";
	}

}
