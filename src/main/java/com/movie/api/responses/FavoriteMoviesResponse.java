package com.movie.api.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.responses.base.BaseResponse;
import com.movie.api.vo.FavoriteMoviesVo;

public class FavoriteMoviesResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -8569828254078451966L;

	@JsonProperty("favorite_movie")
	private List<FavoriteMoviesVo> favoritesMovie;

	public List<FavoriteMoviesVo> getFavoritesMovie() {
		return favoritesMovie;
	}

	public void setFavoritesMovie(List<FavoriteMoviesVo> favoritesMovie) {
		this.favoritesMovie = favoritesMovie;
	}
}