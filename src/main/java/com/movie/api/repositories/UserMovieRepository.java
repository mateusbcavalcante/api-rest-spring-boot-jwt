package com.movie.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movie.api.models.UserMovie;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {
	
	@Query("select u from UserMovie u where u.userId = :userId")
	List<UserMovie> findFavoriteMoviesByUserId(@Param("userId") Integer userId);
	
	@Query("select u from UserMovie u where u.tmdbMediaId = :tmdbMediaId")
	UserMovie findFavoriteMovieByMediaId(@Param("tmdbMediaId") Integer tmdbMediaId);
	
}