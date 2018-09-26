package com.movie.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.constants.BaseConstant;
import com.movie.api.exceptions.ApiException;
import com.movie.api.models.UserMovie;
import com.movie.api.responses.ApiResponse;
import com.movie.api.responses.MarkFavoriteResponse;
import com.movie.api.responses.UserMovieResponse;
import com.movie.api.services.UserMovieService;

@RestController
@RequestMapping("/api/v1/user/movie")
public class UserMovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMovieController.class);

	@Autowired
	private UserMovieService userMovieService;
	
	@RequestMapping(method = RequestMethod.POST, value="/mark_as_favorite", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResponse<MarkFavoriteResponse>> markAsFavorite(@RequestHeader(value = "Authorization") String authorization,
			          												                      @RequestParam(value = "tmdb_movie_id", required = false) Integer tmdbMovieId) {
		
		ApiResponse<MarkFavoriteResponse> responseApi = new ApiResponse<>();
		MarkFavoriteResponse markFavoriteResponse = new MarkFavoriteResponse();
		
		try {
			
			UserMovie userMovie = userMovieService.markAsFavorite(authorization, tmdbMovieId);
			markFavoriteResponse.setMessage(BaseConstant.FAVORITED_MOVIE + userMovie.getTmdbMediaId());
			responseApi.setData(markFavoriteResponse);
			return ResponseEntity.ok(responseApi);
			
		} catch (ApiException ex) {
			LOGGER.error(BaseConstant.INVALID_MOVIE_ID, ex);
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		} 
		catch (UsernameNotFoundException ex) {
			LOGGER.error(BaseConstant.USERNAME_NOT_FOUND, ex);
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/favorites", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResponse<UserMovieResponse>> findFavoriteMoviesByUserId(@RequestHeader(value = "Authorization") String authorization) {
		
		ApiResponse<UserMovieResponse> responseApi = new ApiResponse<>();
		UserMovieResponse userMovieResponse = new UserMovieResponse(); 
		
		try {
			
			userMovieResponse.setListUserMovie(userMovieService.findFavoriteMoviesByUserId(authorization));
			responseApi.setData(userMovieResponse);
			return ResponseEntity.ok(responseApi);
			
		} catch (UsernameNotFoundException ex) {
			LOGGER.error(BaseConstant.USERNAME_NOT_FOUND, ex);
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		}
	}
}