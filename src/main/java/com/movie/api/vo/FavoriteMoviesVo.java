package com.movie.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteMoviesVo {

	@JsonProperty("tmdb_media_id")
	private Integer tmdbMediaId;

	@JsonProperty("favorite_count")
	private Long favoriteCount;

	public FavoriteMoviesVo() {
		
	}
	
	public FavoriteMoviesVo(Integer tmdbMediaId, Long favoriteCount) {
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