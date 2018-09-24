package com.movie.api.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.responses.base.BaseResponse;

public class TopFavoriteResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -8569828254078451966L;

	@JsonProperty("favorite_movie")
	private List<FavoriteMovieResponse> favoritesMovie;

	public List<FavoriteMovieResponse> getFavoritesMovie() {
		return favoritesMovie;
	}

	public void setFavoritesMovie(List<FavoriteMovieResponse> favoritesMovie) {
		this.favoritesMovie = favoritesMovie;
	}
}