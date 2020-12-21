package com.subrat.moviecatalogservice.service;

import java.util.Arrays;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.subrat.moviecatalogservice.model.RatingEntity;
import com.subrat.moviecatalogservice.model.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MovieRatingService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod ="getRatingsFallback" )
    public UserRating getRatings(String userId) {
      return restTemplate.
             getForObject("http://movie-rating-service/ratingdata/users/"+userId,UserRating.class );
    }
    public UserRating getRatingsFallback(String userId) {
        UserRating rating = new UserRating();
        rating.setUserId(userId);
        rating.setUserRating(Arrays.asList(new RatingEntity("100",0)));
        return rating;
    }    
    
}
