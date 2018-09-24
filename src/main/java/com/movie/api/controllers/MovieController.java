package com.movie.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.constants.BaseConstant;
import com.movie.api.exceptions.ApiException;
import com.movie.api.responses.FavoriteMoviesResponse;
import com.movie.api.responses.MessageResponse;
import com.movie.api.responses.MovieResponse;
import com.movie.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<MovieResponse> getMovie(@RequestHeader(value = "Authorization") String authorization,
			                                                    @RequestParam(value = "query", required = true) String query) {

		MovieResponse tmdbResponse = new MovieResponse();
		
		try {
			
			return new ResponseEntity<MovieResponse>(movieService.getMovieByQuery(query), HttpStatus.OK);
			
		} catch (ApiException ex) {
			LOGGER.error(BaseConstant.INVALID_API_KEY, ex);
			tmdbResponse.setMensagem(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
		    return new ResponseEntity<MovieResponse>(tmdbResponse, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_rated", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<MovieResponse> getTopRated(@RequestHeader(value = "Authorization") String authorization) {
		
		MovieResponse tmdbResponse = new MovieResponse();
		
		try {
			
			return new ResponseEntity<MovieResponse>(movieService.getTopRated(), HttpStatus.OK);
			
		} catch (ApiException ex) {
			LOGGER.error(BaseConstant.INVALID_API_KEY, ex);
			tmdbResponse.setMensagem(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
		    return new ResponseEntity<MovieResponse>(tmdbResponse, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_favorited", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<FavoriteMoviesResponse> getTopFavorited(@RequestHeader(value = "Authorization") String authorization) {
		FavoriteMoviesResponse topFavoritoMovieResponse = new FavoriteMoviesResponse(); 
		topFavoritoMovieResponse.setFavoritesMovie(movieService.getTopFavorited());
		return new ResponseEntity<FavoriteMoviesResponse>(topFavoritoMovieResponse, HttpStatus.OK);
	}
}