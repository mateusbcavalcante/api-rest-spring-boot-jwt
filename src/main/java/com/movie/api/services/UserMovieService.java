package com.movie.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.api.constants.BaseConstant;
import com.movie.api.jwt.models.JwtUser;
import com.movie.api.jwt.service.JwtUserDetailsService;
import com.movie.api.jwt.util.JwtTokenUtil;
import com.movie.api.models.UserMovie;
import com.movie.api.repositories.UserMovieRepository;

@Service
public class UserMovieService {

	@Autowired
	private UserMovieRepository userMovieRepository;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public UserMovie markAsFavorite(String authorization, Integer tmdbMovieId) {
		try {
			
			JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authorization));
			return userMovieRepository.save(new UserMovie(jwtUser.getUser().getId(), tmdbMovieId));
			
		} catch (UsernameNotFoundException ex) {
			throw new UsernameNotFoundException(BaseConstant.USERNAME_NOT_FOUND, ex);
		}
	}
	
	public List<UserMovie> findFavoriteMoviesByUserId(String authorization) {
		try {
			
			JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authorization));
			return userMovieRepository.findFavoriteMoviesByUserId(jwtUser.getUser().getId());
			
		} catch (UsernameNotFoundException ex) {
			throw new UsernameNotFoundException(BaseConstant.USERNAME_NOT_FOUND, ex);
		}
	}
}