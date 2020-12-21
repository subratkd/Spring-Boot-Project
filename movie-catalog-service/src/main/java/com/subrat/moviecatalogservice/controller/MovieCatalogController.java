package com.subrat.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.subrat.moviecatalogservice.entity.MovieCatalogEntity;
import com.subrat.moviecatalogservice.model.MovieEntity;
import com.subrat.moviecatalogservice.model.RatingEntity;
import com.subrat.moviecatalogservice.model.UserRating;
import com.subrat.moviecatalogservice.service.MovieInfoService;
import com.subrat.moviecatalogservice.service.MovieRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	@Autowired
	private WebClient.Builder builder;
	@Autowired
	private MovieRatingService movieRatingService;
	@Autowired
	private MovieInfoService movieInfoService;

	@RequestMapping("/{userId}")
	public List<MovieCatalogEntity> getCatalog(@PathVariable("userId")  String userId){
		//pass userid and get all the ratings.
		UserRating ratings = movieRatingService.getRatings(userId);
		//for  each movie id, call movie info service and get details.
		return ratings.getUserRating().stream()
		.map(rating -> movieInfoService.getMovieInfo(rating))
		.collect(Collectors.toList());
	}
	@RequestMapping("/web/{userId}")
	@HystrixCommand(fallbackMethod = "fallBackCatalog")
	public List<MovieCatalogEntity> getCatalogByWebClient(@PathVariable("userId")  String userId){
		//get all rated movie ids
		List<RatingEntity> ratings = Arrays.asList(
				                           new RatingEntity("100", 4),
			                               new RatingEntity("101", 5)
				                     );
		//for  each movie id, call movie info service and get details.
		return ratings.stream().map(rating -> {
			MovieEntity entity  =builder.build()
			.get()
			.uri("http://localhost:8083/movies/movieById?movieId="+rating.getMovieId())
			.retrieve()
			.bodyToMono(MovieEntity.class)
			.block();
			//put them all together.	
		      return new MovieCatalogEntity(entity.getMovieId(),entity.getName(),rating.getRating());
		    }).collect(Collectors.toList());
	}

	public List<MovieCatalogEntity> fallBackCatalog(@PathVariable("userId") String userId) {
		return Arrays.asList(new MovieCatalogEntity("Service Down","",0));
	}

}
