package com.movie.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.movie.api.responses.MessageResponse;
import com.movie.api.responses.UserMovieResponse;
import com.movie.api.services.UserMovieService;

@RestController
@RequestMapping("/api/v1/user/movie")
public class UserMovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMovieController.class);

	@Autowired
	private UserMovieService userMovieService;

	@RequestMapping(method = RequestMethod.POST, value="/mark_as_favorite", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<MessageResponse> markAsFavorite(@RequestHeader(value = "Authorization") String authorization,
			          												    @RequestParam(value = "tmdb_movie_id", required = true) Integer tmdbMovieId) {
		
		try {
			
			userMovieService.markAsFavorite(authorization, tmdbMovieId);
			return new ResponseEntity<MessageResponse>(new MessageResponse(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
			
		} catch (UsernameNotFoundException ex) {
			LOGGER.error(BaseConstant.USERNAME_NOT_FOUND, ex);
			new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
			return new ResponseEntity<MessageResponse>(new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/favorites", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<UserMovieResponse> findFavoriteMoviesByUserId(@RequestHeader(value = "Authorization") String authorization) {
		
		UserMovieResponse userMovieResponse = new UserMovieResponse(); 
		
		try {
			
			userMovieResponse.setListUserMovie(userMovieService.findFavoriteMoviesByUserId(authorization));
			userMovieResponse.setMensagem(new MessageResponse(HttpStatus.OK.value(), "OK"));
			return new ResponseEntity<UserMovieResponse>(userMovieResponse, HttpStatus.OK);
			
		} catch (UsernameNotFoundException ex) {
			LOGGER.error(BaseConstant.USERNAME_NOT_FOUND, ex);
			new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
			return new ResponseEntity<UserMovieResponse>(userMovieResponse, HttpStatus.NOT_FOUND);	
		}
	}
}