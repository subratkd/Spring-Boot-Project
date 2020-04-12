package com.subrat.movieinfoservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrat.movieinfoservice.Entity.MovieEntity;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	@RequestMapping("/movieById")
	public MovieEntity getMovieInfo(@RequestParam  String  movieId){
		return new MovieEntity(movieId,"Test Name");
	}

}
