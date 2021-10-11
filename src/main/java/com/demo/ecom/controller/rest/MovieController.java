/**
 * 
 */
package com.demo.ecom.controller.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author msi
 *
 */
@RestController
@RequestMapping("/v1/movies")
public class MovieController {

	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, path = "/{userId}")
	public List<com.demo.ecom.entity.Movie> getAllMovies(@PathVariable("userId")String userId){
		
		//RestTemplate restTemplate = new RestTemplate();
		
		//List<Rating> ratings = Arrays.asList(new Rating("1",4),new Rating("2", 4));
		
		
		
//		return ratings.stream().map(rating -> {
//		Movie movie = restTemplate.getForObject("http://localhost:8001/movies/" + rating.getMovieId() , Movie.class);
//			return new MovieCatalog(movie.getName(), "Boat Love Story", rating.getRating());
//		}).collect(Collectors.toList());
		return Collections.singletonList(new com.demo.ecom.entity.Movie("Titanic", "Boat Love Story"));
	}
	
}
