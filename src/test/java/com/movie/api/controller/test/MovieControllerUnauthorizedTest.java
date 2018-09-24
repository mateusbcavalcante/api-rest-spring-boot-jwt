package com.movie.api.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.movie.api.jwt.util.JwtTokenUtil;
import com.movie.api.models.JwtUser;
import com.movie.api.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "api.key=12312")
public class MovieControllerUnauthorizedTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private String token;

	@Before
	public void setUp() throws Exception {
		token = jwtTokenUtil.generateToken(new JwtUser(new User("admin", "admin")));
    }

	@Test
	public void shouldBeReturnedKeyFailByMovieSearch() throws Exception {
		mvc.perform(get("/api/v1/movie")
	            .accept(contentType)
	            .header("Authorization", token)
	            .param("query", "Titanic"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldBeReturnedKeyFailBySearchTopRated() throws Exception {
		mvc.perform(get("/api/v1/movie/top_rated")
	            .accept(contentType)
				.header("Authorization", token))
				.andExpect(status().isUnauthorized());
	}
}