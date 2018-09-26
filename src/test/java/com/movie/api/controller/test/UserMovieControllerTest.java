package com.movie.api.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.movie.api.jwt.models.JwtUser;
import com.movie.api.jwt.util.JwtTokenUtil;
import com.movie.api.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserMovieControllerTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	protected MockMvc mvc;
	
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
	public void shouldBeReturnedFavoritedMovie() throws Exception {
		mvc.perform(post("/api/v1/user/movie/mark_as_favorite")
		            .accept(contentType)
		            .header("Authorization", token)
		            .param("tmdb_movie_id", "400"))
					.andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeReturnedUserNotFoundByFavoritedMovie() throws Exception {
		mvc.perform(post("/api/v1/user/movie/mark_as_favorite")
		            .accept(contentType)
		            .header("Authorization", tokenUnauthorized)
		            .param("tmdb_movie_id", "400"))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldBeReturnedFavoritedMoviesByUserWithSuccess() throws Exception {
		mvc.perform(get("/api/v1/user/movie/favorites")
		            .accept(contentType)
					.header("Authorization", token))
					.andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeReturnedUserNotFoundBySearchFavoritedMoviesByUser() throws Exception {
		mvc.perform(get("/api/v1/user/movie/favorites")
		            .accept(contentType)
		            .header("Authorization", tokenUnauthorized))
					.andExpect(status().isBadRequest());
	}
}