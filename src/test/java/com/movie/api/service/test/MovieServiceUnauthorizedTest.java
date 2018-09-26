package com.movie.api.service.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.http.MediaType;

import com.movie.api.responses.TmdbResponse;
import com.movie.api.services.MovieService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(properties = "api.key=12312")
public class MovieServiceUnauthorizedTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

//	@Autowired
	private MovieService movieService;
	
//	@Test(expected = HttpClientErrorException.class)
	public void whenInvalidAPiKey_thenExceptionShouldBeFoundByMovieQuery() throws Exception {
		TmdbResponse movieResponse = movieService.getMovieByQuery("Titanic");
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
	
//	@Test(expected = HttpClientErrorException.class)
	public void whenInvalidAPiKey_thenExceptionShouldBeFoundByTopRated() throws Exception {
		TmdbResponse movieResponse = movieService.getTopRated();
		assertThat(movieResponse.getResults(), not(IsEmptyCollection.empty()));
	}
}