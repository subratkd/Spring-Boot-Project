package com.subrat.movieinfoservice.Entity;

public class MovieEntity {
	private String movieId;
	private String title;
	private String overview;
	
	public MovieEntity(){}
	
    public MovieEntity(String movieId, String title, String ovierview){
		this.movieId = movieId;
		this.title = title;
		this.overview = ovierview;
	}
    
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	
	

}
