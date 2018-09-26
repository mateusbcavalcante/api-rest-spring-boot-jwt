package com.movie.api.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.movie.api.constants.BaseConstant;
import com.movie.api.exceptions.ApiException;
import com.movie.api.models.UserMovie;
import com.movie.api.repositories.UserMovieRepository;
import com.movie.api.responses.FavoriteMovieResponse;
import com.movie.api.responses.TmdbResponse;

@Service
public class MovieService {

	@Autowired
	private UserMovieRepository userMovieRepository;
	
	@Value("${api.key}")
	private String API_KEY;
	
	@Value("${api.url.movie.top.rated}")
	private String API_URL_MOVIE_TOP_RATED;

	@Value("${api.url.movie.query}")
	private String API_URL_MOVIE_QUERY;
	
	public TmdbResponse getTopRated() throws ApiException {
		try {
			
			return new RestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(API_URL_MOVIE_TOP_RATED).queryParam("api_key", API_KEY).toUriString(), HttpMethod.GET, null, TmdbResponse.class).getBody();
			
		} catch (ApiException ex) {
			throw new ApiException(ex.getMessage());
		}
	}
	
	public TmdbResponse getMovieByQuery(String query) throws ApiException {
		try {
			
			if (query == null || query.equalsIgnoreCase("")) {
				throw new ApiException(BaseConstant.INVALID_QUERY);
			}
			
			return new RestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(String.format(API_URL_MOVIE_QUERY, query)).queryParam("api_key", API_KEY).toUriString(), HttpMethod.GET, null, TmdbResponse.class).getBody();
			
		} catch (ApiException ex) {
			throw new ApiException(ex.getMessage());
		}
	}
	
	public List<FavoriteMovieResponse> getTopFavorited() {
		try {
			
			List<FavoriteMovieResponse> listFavoriteMovieVo = new ArrayList<>();
			Map<Integer, Long> favoriteMoviesMap = new LinkedHashMap<>();
			
			userMovieRepository.findAll().stream().collect(Collectors.groupingBy(UserMovie::getTmdbMediaId, Collectors.counting())).entrySet()
			                             .stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
			                             .forEachOrdered(e -> favoriteMoviesMap.put(e.getKey(), e.getValue()));
	        
			favoriteMoviesMap.forEach((k,v)->{listFavoriteMovieVo.add(new FavoriteMovieResponse(k,v));});
	        
			return listFavoriteMovieVo;
			
		} catch (ApiException ex) {
			throw new ApiException(ex.getMessage());
		}
	}
}