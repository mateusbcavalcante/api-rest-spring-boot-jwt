package com.movie.api.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.models.UserMovie;
import com.movie.api.responses.base.BaseResponse;

public class UserMovieResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -8569828254078451966L;

	@JsonProperty("list_user_movie")
	private List<UserMovie> listUserMovie;

	public List<UserMovie> getListUserMovie() {
		return listUserMovie;
	}

	public void setListUserMovie(List<UserMovie> listUserMovie) {
		this.listUserMovie = listUserMovie;
	}
}