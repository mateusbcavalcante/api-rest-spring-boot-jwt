package com.movie.api.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(properties = "api.key=12312")
public class MovieControllerUnauthorizedTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;

//	@Autowired
	protected MockMvc mvc;
	
//	@Test(expected = HttpClientErrorException.class)
	public void shouldBeReturnedKeyFailByMovieSearch() throws Exception {
		mvc.perform(get("/api/v1/movie")
	            .accept(contentType)
	            .param("query", "Titanic"))
				.andExpect(status().isUnauthorized());
	}

//	@Test(expected = HttpClientErrorException.class)
	public void shouldBeReturnedKeyFailBySearchTopRated() throws Exception {
		mvc.perform(get("/api/v1/movie/top_rated")
	            .accept(contentType))
				.andExpect(status().isUnauthorized());
	}
}