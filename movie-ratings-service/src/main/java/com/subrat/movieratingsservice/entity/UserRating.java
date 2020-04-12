package com.subrat.movieratingsservice.entity;

import java.util.List;

public class UserRating {
	private List<RatingEntity> userRating;

	public List<RatingEntity> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<RatingEntity> userRating) {
		this.userRating = userRating;
	}
	
	

}
