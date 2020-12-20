package com.subrat.movieratingsservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrat.movieratingsservice.entity.RatingEntity;
import com.subrat.movieratingsservice.entity.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class MovieRatingController {
    @RequestMapping("/{movieId}")
	public RatingEntity getRating(@PathVariable("movieId") String movieId){
		return new RatingEntity(movieId,5);
	}
    @RequestMapping("/users/{userId}")
	public UserRating getRatingByUserId(@PathVariable("userId") String userId){
    	List<RatingEntity> ratingList =   Arrays.asList(
                new RatingEntity("100", 2),
                new RatingEntity("101", 3)
          );
		UserRating rating = new UserRating();
		rating.setUserRating(ratingList);
		return rating;
	}
}
