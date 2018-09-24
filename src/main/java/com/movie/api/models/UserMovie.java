package com.movie.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer userId;

	private Integer tmdbMediaId;

	public UserMovie() {

	}

	public UserMovie(Integer userId, Integer tmdbMediaId) {
		this.userId = userId;
		this.tmdbMediaId = tmdbMediaId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getTmdbMediaId() {
		return tmdbMediaId;
	}
}