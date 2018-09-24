package com.movie.api.service.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.api.exceptions.ApiException;
import com.movie.api.responses.TmdbResponse;
import com.movie.api.services.MovieService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "api.key=12312")
public class MovieServiceUnauthorizedTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	private MovieService movieService;
	
	@Test(expected = ApiException.class)
	public void whenInvalidAPiKey_thenExceptionShouldBeFoundByMovieQuery() throws Exception {
		TmdbResponse movieResponse = movieService.getMovieByQuery("Titanic");
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
	
	@Test(expected = ApiException.class)
	public void whenInvalidAPiKey_thenExceptionShouldBeFoundByTopRated() throws Exception {
		TmdbResponse movieResponse = movieService.getTopRated();
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
}