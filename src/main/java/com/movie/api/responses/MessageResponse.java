package com.movie.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {

	@JsonProperty("status_code")
	private int statusCode;

	@JsonProperty("status_message")
	private String statusMessage;

	public MessageResponse(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}