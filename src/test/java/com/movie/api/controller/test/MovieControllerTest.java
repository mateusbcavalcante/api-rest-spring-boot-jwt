package com.movie.api.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	protected MockMvc mvc;
	
	@Test
	public void shouldBeReturnedMovieSearchedByNameWithSuccess() throws Exception {
		
		mvc.perform(get("/api/v1/movie")
		            .accept(contentType)
		            .param("query", "Titanic"))
					.andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeReturnedBadRequestByMovieSearchedNameWith() throws Exception {
		
		mvc.perform(get("/api/v1/movie")
		            .accept(contentType)
		            .param("query", ""))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldBeReturnedBestMoviesWithSuccess() throws Exception {
		mvc.perform(get("/api/v1/movie/top_rated")
		            .accept(contentType))
					.andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeReturnedFavoritedMoviesWithSuccess() throws Exception {
		mvc.perform(get("/api/v1/movie/top_favorited")
		            .accept(contentType))
					.andExpect(status().isOk());
	}
}