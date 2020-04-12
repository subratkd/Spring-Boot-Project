package com.subrat.moviecatalogservice.cotroller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.subrat.moviecatalogservice.entity.MovieCatalogEntity;
import com.subrat.moviecatalogservice.model.MovieEntity;
import com.subrat.moviecatalogservice.model.RatingEntity;
import com.subrat.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder builder;
	@RequestMapping("/{userId}")
	public List<MovieCatalogEntity> getCatalog(@PathVariable("userId")  String userId){
		//pass userid and get all the ratings.
		UserRating ratings = restTemplate.getForObject("http://movie-rating-service/ratingdata/users/sd491w",UserRating.class );
		//for  each movie id, call movie info service and get details.
		return ratings.getUserRating().stream().map(rating -> {
			MovieEntity entity = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/movieById?movieId="+rating.getMovieId(), MovieEntity.class);
			System.out.println("entity====>"+entity);
			//put them all together.	
		      return new MovieCatalogEntity(entity.getMovieId(),entity.getName(),rating.getRating());
		    }).collect(Collectors.toList());
			
	}
	@RequestMapping("/web/{userId}")
	public List<MovieCatalogEntity> getCatalogByWebClient(@PathVariable("userId")  String userId){
		//get all rated movie ids
		List<RatingEntity> ratings = Arrays.asList(
				                           new RatingEntity("1234", 4),
			                               new RatingEntity("5678", 5)
				                     );
		//for  each movie id, call movie info service and get details.
		return ratings.stream().map(rating -> {
			MovieEntity entity  =builder.build()
			.get()
			.uri("http://localhost:8083/movies/movieById?movieId="+rating.getMovieId())
			.retrieve()
			.bodyToMono(MovieEntity.class)
			.block();
			System.out.println("entity====>"+entity);
			//put them all together.	
		      return new MovieCatalogEntity(entity.getMovieId(),entity.getName(),rating.getRating());
		    }).collect(Collectors.toList());
	}

}
