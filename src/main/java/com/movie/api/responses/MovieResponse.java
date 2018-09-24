package com.movie.api.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.api.responses.base.BaseResponse;
import com.movie.api.vo.MovieVo;

public class MovieResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 967760234570489962L;

	@JsonProperty("page")
	private Integer page;

	@JsonProperty("total_results")
	private Integer totalResults;

	@JsonProperty("total_pages")
	private Integer totalPages;

	@JsonProperty("results")
	private List<MovieVo> results;

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

	public List<MovieVo> getResults() {
		return results;
	}

	public void setResults(List<MovieVo> results) {
		this.results = results;
	}
}