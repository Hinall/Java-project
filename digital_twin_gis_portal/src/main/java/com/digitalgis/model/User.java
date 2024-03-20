package com.digitalgis.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "user_id")
	private Integer userId;

	@JsonProperty(value = "user_name")
	private String userName;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "email_id")
	private String emailid;

	@JsonProperty(value = "contact_no")
	private String contactNo;

	@JsonProperty(value = "role_name")
	private String rolename;

	@JsonProperty(value = "is_currently_login")
	private boolean iscurrentlylogin;

	private Date lastlogindatetime;
	private Date createddatetime;
	private Date modifiedon;
	private boolean isdeleted;

	private boolean status;

	@JsonProperty(value = "role_id")
	private int userrole;
	private String resettoken;

	private int questionid;
	private String answer;
	private String createdby;
	private int modifiedby;

	@JsonProperty(value = "date_of_birth")
	private Date dateofbirth;

	@JsonProperty(value = "new_password")
	private String newPassword;

	public User() {
	}

	public User(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getLastlogindatetime() {
		return lastlogindatetime;
	}

	public void setLastlogindatetime(Date lastlogindatetime) {
		this.lastlogindatetime = lastlogindatetime;
	}

	public Date getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(Date createddatetime) {
		this.createddatetime = createddatetime;
	}

	public Date getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public boolean getIscurrentlylogin() {
		return iscurrentlylogin;
	}

	public void setIscurrentlylogin(boolean iscurrentlylogin) {
		this.iscurrentlylogin = iscurrentlylogin;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUserrole() {
		return userrole;
	}

	public void setUserrole(int userrole) {
		this.userrole = userrole;
	}

	public String getResettoken() {
		return resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public int getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
