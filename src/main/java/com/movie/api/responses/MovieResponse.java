package com.movie.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieResponse {

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("adult")
	private Boolean adult;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("genre_ids")
	private Integer[] genreIds;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("title")
	private String title;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("popularity")
	private Long popularity;

	@JsonProperty("vote_count")
	private Integer voteCount;

	@JsonProperty("video")
	private Boolean video;

	@JsonProperty("vote_average")
	private String voteAverage;

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer[] getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(Integer[] genreIds) {
		this.genreIds = genreIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public Long getPopularity() {
		return popularity;
	}

	public void setPopularity(Long popularity) {
		this.popularity = popularity;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Boolean getVideo() {
		return video;
	}

	public void setVideo(Boolean video) {
		this.video = video;
	}

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}
}