package com.movie.api.service.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.api.responses.MovieResponse;
import com.movie.api.services.MovieService;
import com.movie.api.vo.FavoriteMoviesVo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieServiceTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

	@Autowired
	private MovieService movieService;
	
	@Test
	public void whenValidQuery_thenMovieShouldBeFound() throws Exception {
		MovieResponse movieResponse = movieService.getMovieByQuery("Titanic");
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
	
	@Test
	public void shouldBeReturnedBestMoviesWithSuccess() throws Exception {
		MovieResponse movieResponse = movieService.getTopRated();
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
	
	@Test
	public void shouldBeReturnedFavoritedMoviesWithSuccess() throws Exception {
		List<FavoriteMoviesVo> listFavoritedMovies = movieService.getTopFavorited();
		assertThat(listFavoritedMovies, not(IsEmptyCollection.empty()));
	}
}