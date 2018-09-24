package com.movie.api.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse implements Serializable {

	private static final long serialVersionUID = -8569828254078451966L;

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