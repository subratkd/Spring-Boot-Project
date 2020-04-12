package com.subrat.moviecatalogservice.model;

public class RatingEntity {
	private String movieId;
	private int  rating;
	public RatingEntity(){}
	public RatingEntity(String movieId, int rating){
		this.movieId = movieId;
		this.rating = rating;
	}
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
