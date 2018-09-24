package com.movie.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAuthenticationRequest {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public JwtAuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}