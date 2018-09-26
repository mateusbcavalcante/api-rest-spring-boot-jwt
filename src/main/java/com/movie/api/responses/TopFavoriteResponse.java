package com.movie.api.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopFavoriteResponse {

	@JsonProperty("favorite_movie")
	private List<FavoriteMovieResponse> favoritesMovie;

	public List<FavoriteMovieResponse> getFavoritesMovie() {
		return favoritesMovie;
	}

	public void setFavoritesMovie(List<FavoriteMovieResponse> favoritesMovie) {
		this.favoritesMovie = favoritesMovie;
	}
}