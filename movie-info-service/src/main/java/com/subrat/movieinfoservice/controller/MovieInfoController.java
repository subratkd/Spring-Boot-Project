package com.subrat.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.subrat.movieinfoservice.Entity.MovieEntity;
import com.subrat.movieinfoservice.Entity.MovieSummary;


@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	RestTemplate restTemplate;


	@RequestMapping("/movieById")
	public MovieEntity getMovieInfo(@RequestParam  String  movieId){
		MovieSummary movieSummary = restTemplate.getForObject(
			"https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey,
			MovieSummary.class
		);
		
		return new MovieEntity(movieId,movieSummary.getTitle(), movieSummary.getOverview());
	}

}
