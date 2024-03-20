package com.digitalgis.jwt;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse  {
	private String token;
	private String type = "Bearer";
	private Collection<? extends GrantedAuthority> authorities;
	private String user_name;
//	private String contact_no;

	public JwtResponse(String accessToken, Collection<? extends GrantedAuthority> authorities, String user_name) {
		this.token = accessToken;
		this.authorities = authorities;
		this.user_name = user_name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

//	public String getContact_no() {
//		return contact_no;
//	}
//
//	public void setContact_no(String contact_no) {
//		this.contact_no = contact_no;
//	}

}
