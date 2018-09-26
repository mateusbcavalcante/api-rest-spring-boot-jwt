package com.movie.api.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.models.UserMovie;

public class UserMovieResponse {

	@JsonProperty("list_user_movie")
	private List<UserMovie> listUserMovie;

	public List<UserMovie> getListUserMovie() {
		return listUserMovie;
	}

	public void setListUserMovie(List<UserMovie> listUserMovie) {
		this.listUserMovie = listUserMovie;
	}
}