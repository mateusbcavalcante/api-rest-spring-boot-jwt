package com.movie.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

	@JsonProperty("token")
	private String token;

	public AuthenticationResponse(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}