package com.movie.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequest {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public AuthenticationRequest(String username, String password) {
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