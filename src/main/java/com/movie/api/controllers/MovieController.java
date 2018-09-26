package com.movie.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.exceptions.ApiException;
import com.movie.api.responses.ApiResponse;
import com.movie.api.responses.TmdbResponse;
import com.movie.api.responses.TopFavoriteResponse;
import com.movie.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResponse<TmdbResponse>> getMovie(@RequestParam(value = "query") String query) {
		
		ApiResponse<TmdbResponse> responseApi = new ApiResponse<TmdbResponse>();
		
		try {
			
			TmdbResponse tmdbResponse = movieService.getMovieByQuery(query);
			responseApi.setData(tmdbResponse);
			return ResponseEntity.ok(responseApi);
			
		} catch (ApiException ex) {
			LOGGER.error(ex.getMessage());
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_rated", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResponse<TmdbResponse>> getTopRated() {
		
		ApiResponse<TmdbResponse> responseApi = new ApiResponse<TmdbResponse>();
		
		try {
			
			TmdbResponse tmdbResponse = movieService.getTopRated();
			responseApi.setData(tmdbResponse);
			return ResponseEntity.ok(responseApi);
			
		} catch (ApiException ex) {
			LOGGER.error(ex.getMessage());
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_favorited", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResponse<TopFavoriteResponse>> getTopFavorited() {
		
		ApiResponse<TopFavoriteResponse> responseApi = new ApiResponse<TopFavoriteResponse>();
		
		try {
			
			TopFavoriteResponse topFavoritoMovieResponse = new TopFavoriteResponse(); 
			topFavoritoMovieResponse.setFavoritesMovie(movieService.getTopFavorited());
			responseApi.setData(topFavoritoMovieResponse);
			return ResponseEntity.ok(responseApi);
			
		} catch (ApiException ex) {
			LOGGER.error(ex.getMessage());
			responseApi.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(responseApi);
		}
	}
}