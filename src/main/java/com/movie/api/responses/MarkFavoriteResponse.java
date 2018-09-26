package com.movie.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarkFavoriteResponse {

	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}