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
import com.movie.api.responses.TopFavoriteResponse;
import com.movie.api.responses.MessageResponse;
import com.movie.api.responses.TmdbResponse;
import com.movie.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<TmdbResponse> getMovie(@RequestHeader(value = "Authorization") String authorization,
			                                                    @RequestParam(value = "query", required = true) String query) {

		TmdbResponse tmdbResponse = new TmdbResponse();
		
		try {
			
			return new ResponseEntity<TmdbResponse>(movieService.getMovieByQuery(query), HttpStatus.OK);
			
		} catch (ApiException ex) {
			LOGGER.error(BaseConstant.INVALID_API_KEY, ex);
			tmdbResponse.setMensagem(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
		    return new ResponseEntity<TmdbResponse>(tmdbResponse, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_rated", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<TmdbResponse> getTopRated(@RequestHeader(value = "Authorization") String authorization) {
		
		TmdbResponse tmdbResponse = new TmdbResponse();
		
		try {
			
			return new ResponseEntity<TmdbResponse>(movieService.getTopRated(), HttpStatus.OK);
			
		} catch (ApiException ex) {
			LOGGER.error(BaseConstant.INVALID_API_KEY, ex);
			tmdbResponse.setMensagem(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
		    return new ResponseEntity<TmdbResponse>(tmdbResponse, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/top_favorited", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<TopFavoriteResponse> getTopFavorited(@RequestHeader(value = "Authorization") String authorization) {
		TopFavoriteResponse topFavoritoMovieResponse = new TopFavoriteResponse(); 
		topFavoritoMovieResponse.setFavoritesMovie(movieService.getTopFavorited());
		return new ResponseEntity<TopFavoriteResponse>(topFavoritoMovieResponse, HttpStatus.OK);
	}
}