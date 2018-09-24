package com.movie.api.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.responses.base.BaseResponse;

public class TmdbResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 967760234570489962L;

	@JsonProperty("page")
	private Integer page;

	@JsonProperty("total_results")
	private Integer totalResults;

	@JsonProperty("total_pages")
	private Integer totalPages;

	@JsonProperty("results")
	private List<MovieResponse> results;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<MovieResponse> getResults() {
		return results;
	}

	public void setResults(List<MovieResponse> results) {
		this.results = results;
	}
}