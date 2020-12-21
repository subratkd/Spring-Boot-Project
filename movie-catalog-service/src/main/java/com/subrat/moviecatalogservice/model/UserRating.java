package com.subrat.moviecatalogservice.model;

import java.util.List;

public class UserRating {
	private String userId;
	private List<RatingEntity> userRating;

	public List<RatingEntity> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<RatingEntity> userRating) {
		this.userRating = userRating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
