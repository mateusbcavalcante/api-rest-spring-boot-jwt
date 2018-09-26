package com.movie.api.service.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.api.exceptions.ApiException;
import com.movie.api.jwt.models.JwtUser;
import com.movie.api.jwt.util.JwtTokenUtil;
import com.movie.api.models.User;
import com.movie.api.models.UserMovie;
import com.movie.api.repositories.UserMovieRepository;
import com.movie.api.services.UserMovieService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserMovieServiceTest {
	
	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	private UserMovieService userMovieService;
	
	@Autowired
	private UserMovieRepository userMovieRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private String token;
	
	private String tokenUnauthorized;

	@Before
	public void setUp() throws Exception {
		token = jwtTokenUtil.generateToken(new JwtUser(new User("admin", "admin")));
		tokenUnauthorized = jwtTokenUtil.generateToken(new JwtUser(new User("adminUnauthorized", "password")));
    }
	
	@Test
	public void whenValidToken_thenShouldBeSaveFavoriteMovieByUser() throws Exception {
		userMovieService.markAsFavorite(token, 202);
		Assert.assertNotNull(userMovieRepository.findFavoriteMovieByMediaId(202));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void whenInvalidToken_thenExceptionShouldBeFoundBySaveFavoriteMovieByUser() throws Exception {
		userMovieService.markAsFavorite(tokenUnauthorized, 204);
		Assert.assertNull(userMovieRepository.findFavoriteMovieByMediaId(204));
	}
	
	@Test(expected = ApiException.class)
	public void whenInvalidToken_thenExceptionShouldBeExceptionBySaveFavoriteMovieByUserAndMediaIdNull() throws Exception {
		userMovieService.markAsFavorite(tokenUnauthorized, null);
		Assert.assertNull(userMovieRepository.findFavoriteMovieByMediaId(null));
	}
	
	@Test
	public void whenValidToken_thenShouldBeFoundByMovieQuery() throws Exception {
		List<UserMovie> listUserMovie = userMovieService.findFavoriteMoviesByUserId(token);
		assertThat(listUserMovie, not(IsEmptyCollection.empty()));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void whenInvalidToken_thenExceptionShouldBeFoundByMovieQuery() throws Exception {
		List<UserMovie> listUserMovie = userMovieService.findFavoriteMoviesByUserId(tokenUnauthorized);
		assertThat(listUserMovie, IsEmptyCollection.empty());
	}
}