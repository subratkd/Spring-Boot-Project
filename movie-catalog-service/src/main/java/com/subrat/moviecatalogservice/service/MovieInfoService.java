package com.subrat.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.subrat.moviecatalogservice.entity.MovieCatalogEntity;
import com.subrat.moviecatalogservice.model.MovieEntity;
import com.subrat.moviecatalogservice.model.RatingEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MovieInfoService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod ="getMovieInfoFallback" )
    public MovieCatalogEntity getMovieInfo(RatingEntity rating) {
        MovieEntity entity = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/movieById?movieId="+rating.getMovieId(), MovieEntity.class);
        return new MovieCatalogEntity(entity.getMovieId(),entity.getName(),rating.getRating());
    }
    public MovieCatalogEntity getMovieInfoFallback(RatingEntity rating) {
        return new MovieCatalogEntity("","",rating.getRating());
    }   
    
}
