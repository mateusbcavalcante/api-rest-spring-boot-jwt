package com.movie.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteMovieResponse {

	@JsonProperty("tmdb_media_id")
	private Integer tmdbMediaId;

	@JsonProperty("favorite_count")
	private Long favoriteCount;

	public FavoriteMovieResponse() {
		
	}
	
	public FavoriteMovieResponse(Integer tmdbMediaId, Long favoriteCount) {
		this.tmdbMediaId = tmdbMediaId;
		this.favoriteCount = favoriteCount;
	}
	
	public Integer getTmdbMediaId() {
		return tmdbMediaId;
	}

	public void setTmdbMediaId(Integer tmdbMediaId) {
		this.tmdbMediaId = tmdbMediaId;
	}

	public Long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
}